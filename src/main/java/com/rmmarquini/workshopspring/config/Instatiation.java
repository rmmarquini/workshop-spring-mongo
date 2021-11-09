package com.rmmarquini.workshopspring.config;

import com.rmmarquini.workshopspring.domain.Post;
import com.rmmarquini.workshopspring.domain.User;
import com.rmmarquini.workshopspring.dto.AuthorDTO;
import com.rmmarquini.workshopspring.dto.CommentDTO;
import com.rmmarquini.workshopspring.repository.PostRepository;
import com.rmmarquini.workshopspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instatiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        // Initialization
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));

        // Clean up database
        userRepository.deleteAll();
        postRepository.deleteAll();

        // Setting users
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        // Setting posts
        Post post1 = new Post(
                null,
                sdf.parse("09/11/2021"),
                "Partiu viagem",
                "Vou viajar para Puglia na Itália. Abraços!",
                new AuthorDTO(alex)
        );

        Post post2 = new Post(
                null,
                sdf.parse("07/10/2021"),
                "Bom dia",
                "Hoje acordei me sentindo bem!",
                new AuthorDTO(maria)
        );

        postRepository.saveAll(Arrays.asList(post1, post2));

        // Associating posts to users
        maria.getPosts().addAll(Arrays.asList(post2));
        alex.getPosts().addAll(Arrays.asList(post1));

        userRepository.saveAll(Arrays.asList(maria, alex));

        // Setting posts comments
        CommentDTO c1 = new CommentDTO(
                "Boa viagem mano!",
                sdf.parse("09/11/2021"),
                new AuthorDTO(bob)
        );

        CommentDTO c2 = new CommentDTO(
                "Aproveite!",
                sdf.parse("09/11/2021"),
                new AuthorDTO(maria)
        );

        CommentDTO c3 = new CommentDTO(
                "Tenha um ótimo dia!",
                sdf.parse("07/10/2021"),
                new AuthorDTO(alex)
        );

        // Associating comments to posts
        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

    }

}
