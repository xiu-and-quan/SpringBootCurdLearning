package com.example.curd_02.service.imp;

import com.example.curd_02.entity.Animal;
import com.example.curd_02.jopo.dto.PetDTO;
import com.example.curd_02.jopo.dto.PetMoreItemDTO;
import com.example.curd_02.jopo.vo.AnimalVO;
import com.example.curd_02.jopo.vo.PetMoreItemVO;
import com.example.curd_02.jopo.vo.PetVO;
import com.example.curd_02.repository.PetRepository;
import com.example.curd_02.service.PetService;
import com.example.curd_02.until.PageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.net.JARSigningException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;


@Service
@Slf4j
public class PetServiceImp implements PetService {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Autowired
    private PetRepository petRepository;

    /**
     * 添加新用户
     * @param petDTO
     * @return
     */
    @Override
    public AnimalVO save(PetDTO petDTO) {
        /**
         * 服务层处理业务逻辑
         */
        Animal animal = new Animal();
        animal.setName(petDTO.getName());
        animal.setDateTimeModified(petDTO.getDatetimeModified());
        Animal animal1 = petRepository.save(animal);

        AnimalVO animalVO = new AnimalVO();
        animalVO.setName(animal.getName());
        animalVO.setDateTimeModified(animal1.getDateTimeModified());
        return animalVO;
    }

    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PageResult<AnimalVO> getByPage(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<Animal> page = petRepository.findAll(pageable);
        //对数据Page<Animal>去冗余字段
        List<AnimalVO> animalVOList = convert(page);
        return new PageResult<>(page.getTotalElements(),page.getTotalPages(),animalVOList);
//        return animalVOList;

    }

    /**
     * 更新信息
     * @param petDTO
     * @return
     */
    @Override
    public AnimalVO update(PetDTO petDTO) {
        Animal animal = new Animal();
        animal.setId(petDTO.getId());
        animal.setName(petDTO.getName());
        animal.setDateTimeModified(petDTO.getDatetimeModified());
        Animal animal1 = petRepository.save(animal);

        AnimalVO animalVO = new AnimalVO();
        animalVO.setName(animal.getName());
        animalVO.setDateTimeModified(animal1.getDateTimeModified());
        return animalVO;
    }

    @Override
    public List<PetVO> findAll() {
        return petRepository.findALlInterface();
    }

    /**
     * 数据转换 将page转成pageResult<AnimalVO>
     */
    private List<AnimalVO> convert(Page<Animal> page) {
        List<AnimalVO> resListAnimalVO = new ArrayList<>();
//        page.forEach(animalItem -> {
//            AnimalVO animalVO = new AnimalVO();
//            BeanUtils.copyProperties(animalItem,animalVO);//把animalItem对应animalVO的字段名赋值
//            resListAnimalVO.add(animalVO);
//        });
        /** 第二种是用page.stream().map遍历 */
        List<AnimalVO> animalVOS = page.stream().map((e)->{
                    AnimalVO animalVO = new AnimalVO();
                    BeanUtils.copyProperties(e,animalVO);
                    return animalVO;
                }
        ).collect(Collectors.toList());
        return animalVOS;
    }

    /**
     * 查询多出的属性，用接口类型接收
     * @return
     */
    @Override
    public List<PetMoreItemVO> fingCount() {
        /**
         * 第一种借助方法
         */
//        List<PetMoreItemDTO> listFindCount = petRepository.findCount();
//        //接收的接口类型，需要转换 转成json理由是数据库里面的日期时间是没有办法直接转成字符串或者LocalDateTime类型的 json是一种字符，就可以自由转
//        ObjectMapper objectMapper = new ObjectMapper();
//        //设置序列化的日期格式
////        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        //java8不支持localDateTime，需要注册和引入依赖
//        objectMapper.findAndRegisterModules();
//        try{
//            String resJson = objectMapper.writeValueAsString(listFindCount);
//            List<PetMoreItemVO>  list = objectMapper.readValue(resJson, new TypeReference<List<PetMoreItemVO>>() {
//            });
////            List list = objectMapper.readValue(resJson, List.class);
//            list.forEach(System.out::println);
//            return list;
//        }catch (JsonProcessingException e){
//            log.error("查询结果格式准换失败",e);
//        }
//        //可以不往抛异常，返回值为null
//        return null;
        /**
         * 关于ObjectMapper中的objectMapper.readValue
         */
        /**
         * 第二种借助方法 日期类型ObjectMapper默认是时间戳格式的，需要设置一下才行
         * 取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
         * mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
         * mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
         */
        List<PetMoreItemDTO> listRes= petRepository.findCount();
        //遍历方法
        List<PetMoreItemVO> lists = new ArrayList<>();
        listRes.forEach(p->{
            lists.add(
                    PetMoreItemVO.builder()
                            .id(p.getId())
                            .name(p.getName())
                            .dateTimeModified(p.getDateTimeModified())
                            .nums(p.getNums())
                            .build()
            );
        });
        return lists;
    }

    @Override
    public Page<Animal> queryByPage(PetDTO petDTO, Pageable page) {
          LocalDateTime localDateTime = null;
          DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          localDateTime = LocalDateTime.of(LocalDate.parse(petDTO.getStrDatetimeModified(), df), LocalTime.of(0,0,0));
          petDTO.setDatetimeModified(localDateTime);
          return petRepository.queryByPage(petDTO,page);
//        //日期转换 接收进来的日期是String类型的
//        DateTimeFormatter dfDate = DateTimeFormatter.ofPattern(DATE_PATTERN);
//        //要转换的类型
//        DateTimeFormatter dfDateTime = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
//        LocalDateTime localDateTime = null;
//        //进行转换
//        String str = petDTO.getStrDatetimeModified();
//        LocalDate localDate = LocalDate.parse(str, dfDate);
//        LocalTime localTime =LocalTime.of(0,0,0);
//        localDateTime =LocalDateTime.of(localDate,localTime);
////        LocalDateTime localTime = LocalDateTime.parse(petDTO.getStrDatetimeModified(),dfDateTime);
//        petDTO.setDatetimeModified(localDateTime);
//        Page<Animal> res = petRepository.queryByPage(petDTO, page);
//        return res;
    }
}

//时间处理工具类

