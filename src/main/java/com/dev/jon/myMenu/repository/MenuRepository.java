package com.dev.jon.myMenu.repository;

import com.dev.jon.myMenu.entitys.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}