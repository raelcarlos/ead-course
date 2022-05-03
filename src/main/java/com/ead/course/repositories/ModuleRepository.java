package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {

    // o EntityGraph serve pra que, em tempo de execução, eu tenha a opção de carregar o ModuleModel com o
    // atributado "course" carregado, já que por default é um LAZY.
    // Nao vamos usar por enquanto.
    // @EntityGraph(attributePaths = {"course"})
    //    // ModuleModel findByTitle(String title);

    @Query(value="select * from tb_modules where course_course_id = :courseId", nativeQuery = true)
    List<ModuleModel> findAllModulesIntoCourse(@Param("courseId") UUID courseId);

}
