package com.example.curd_02.controller;

import com.example.curd_02.entity.Animal;
import com.example.curd_02.jopo.dto.PetDTO;
import com.example.curd_02.jopo.vo.AnimalVO;
import com.example.curd_02.jopo.vo.PetMoreItemVO;
import com.example.curd_02.jopo.vo.PetVO;
import com.example.curd_02.service.PetService;
import com.example.curd_02.until.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    private PetService petService;

    /**
     * 增加动物信息
     */
    @PostMapping("/add")
    public AnimalVO save(@RequestBody PetDTO petDTO){
        System.out.println(petDTO);
        return petService.save(petDTO);
    }

    /**
     * 分页获取事件列表
     */
    @GetMapping("/page")
    public PageResult<AnimalVO> getByPage(@RequestParam(defaultValue = "1") Integer pageIndex,
                                    @RequestParam(defaultValue = "2") Integer pageSize
    ){
        PageResult<AnimalVO> byPage = petService.getByPage(pageIndex, pageSize);
        return byPage;
    }

    /**
     * 更新信息
     */
    @PostMapping("/update")
    public AnimalVO update(@RequestBody PetDTO petDTO){
        System.out.println(petDTO);
        return petService.update(petDTO);
    }

    /**
     * 查询所有信息
     */
    @GetMapping("/finaAll")
    public List<PetVO> finaAll(){
        return petService.findAll();
    }

    /**
     * 当查询的分结果会多出自己没有的属性的时候，用jpa完成的话，需要接口去接，并且需要转换
     * 查询用户信息，已经总的数量
     */
    @GetMapping("/fingCount")
    public List<PetMoreItemVO> fingCount(){
        return petService.fingCount();
    }

    /**
     * 条件查询加分页 getMapping 通过键来给接收的对象赋值
     */
    @GetMapping("/selectBypage")
    public Page<Animal> page(@RequestParam(defaultValue = "1") Integer pageIndex,
                             @RequestParam(defaultValue = "3") Integer pageSize,
                             PetDTO petDTO){
        Pageable page = PageRequest.of(pageIndex - 1, pageSize);
        return petService.queryByPage(petDTO, page);
    }

}
