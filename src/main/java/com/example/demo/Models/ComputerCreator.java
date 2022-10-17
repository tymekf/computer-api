package com.example.demo.Models;

import com.example.demo.Models.Computer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComputerCreator {

    public List<Computer> createComputers() {
        List<Computer> computersList = new ArrayList<>();
        computersList.add(new Computer("komputer 1", LocalDate.of(2022, 1, 3), 345));
        computersList.add(new Computer("komputer 2", LocalDate.of(2022, 1, 10), 543));
        computersList.add(new Computer("komputer 3", LocalDate.of(2022, 1, 3), 346));
        return computersList;
    }

}
