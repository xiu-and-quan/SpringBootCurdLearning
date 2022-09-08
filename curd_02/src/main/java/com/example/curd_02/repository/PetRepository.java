package com.example.curd_02.repository;

import com.example.curd_02.entity.Animal;
import com.example.curd_02.jopo.dto.PetDTO;
import com.example.curd_02.jopo.dto.PetMoreItemDTO;
import com.example.curd_02.jopo.vo.AnimalVO;
import com.example.curd_02.jopo.vo.PetMoreVO;
import com.example.curd_02.jopo.vo.PetVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PetRepository extends JpaRepository<Animal,Integer> {
    @Query(value = "select an.id as id,an.datetime_modified as datetimeModified,an.name as name from animal an",nativeQuery = true)
    List<PetVO> findALlInterface();

    /**
     * 查询额外属性
     * 1、保存再map里面
     * 2、用自定义接口类型来接收
     * @return
     */
    @Query(value = "select "+
            "an.id as id, "+
            "an.name as name, "+
            "an.datetime_modified as dateTimeModified, "+
            "count(*) as nums "+
            "from animal an " +
            "group by an.id",nativeQuery = true)
//    List<Map<String,Object>> findCount();
    List<PetMoreItemDTO> findCount();

    //countProjection = "id",
    @Query(value = "SELECT * " +
            "FROM animal " +
            "WHERE " +
            "IF(:#{#petDTO.datetimeModified} IS NOT NULL, datetime_modified >= :#{#petDTO.datetimeModified}, 1=1)"+
            "ORDER BY id DESC"
            ,nativeQuery = true)
    Page<Animal> queryByPage(@Param("petDTO")PetDTO petDTO, Pageable page);

    //用新的类接收数据，用的jpa的方法，用构造函数接收
    @Query(value = "select new com.example.curd_02.jopo.vo.PetMoreVO(an.id,an.name,an.dateTimeModified,count(*)) from Animal an GROUP BY an.id")
    List<PetMoreVO> findInformation();
}
