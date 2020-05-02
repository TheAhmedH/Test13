package com.example.jokesjava;

import java.util.Random;

public class JokeTeller {

    public String MakeJoke() {

        //Adding an array of jokes in jokeData
        String[] jokeData = {
                "Some people see a problem and think I know, I’ll use Java \n Now they have a ProblemFactory.\n",
                "If you put a million monkeys at a million keyboards, one of them will eventually write a Java program. The rest of them will write Perl programs.\n",
                "Java Programmer 1: We have a problem in our website\n" +
                        "\n" +
                        "Java Programmer 2: Let’s use Struts!\n" +
                        "\n" +
                        "Java Programmer 1: Now we have two problems.",
                " Java and C were telling jokes. It was C’s turn, so he writes something on the wall, points to it and says Do you get the reference? \n But Java didn’t."
        };
        //Selecting and returning a random joke
        return jokeData[new Random().nextInt(jokeData.length)];
    }
}
