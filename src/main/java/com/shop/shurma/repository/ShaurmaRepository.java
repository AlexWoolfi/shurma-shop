package com.shop.shurma.repository;

import com.shop.shurma.entity.Shaurma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShaurmaRepository extends JpaRepository<Shaurma,Long> {
//    List<Shaurma> findByTitle(String title);

}
