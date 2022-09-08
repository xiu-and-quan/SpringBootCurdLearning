package com.example.curd_02;

import com.example.curd_02.entity.Animal;
import com.example.curd_02.jopo.vo.PetMoreVO;
import com.example.curd_02.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Curd02ApplicationTests {

    @Autowired
    private PetRepository petRepository;

    @Test
    void findInformation() {
        List<PetMoreVO> res = petRepository.findInformation();
        res.forEach(System.out::println);
    }

    @Test
    void findAll() {
        List<Animal> res = petRepository.findAll();
        res.forEach(System.out::println);
    }

}
