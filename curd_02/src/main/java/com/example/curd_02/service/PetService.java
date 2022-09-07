package com.example.curd_02.service;

import com.example.curd_02.entity.Animal;
import com.example.curd_02.jopo.dto.PetDTO;
import com.example.curd_02.jopo.vo.AnimalVO;
import com.example.curd_02.jopo.vo.PetMoreItemVO;
import com.example.curd_02.jopo.vo.PetVO;
import com.example.curd_02.until.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetService {

    AnimalVO save(PetDTO petDTO);

    PageResult<AnimalVO> getByPage(Integer pageIndex, Integer pageSize);

    AnimalVO update(PetDTO petDTO);

    List<PetVO> findAll();

    List<PetMoreItemVO> fingCount();

    Page<Animal> queryByPage(PetDTO petDTO, Pageable page);
}
