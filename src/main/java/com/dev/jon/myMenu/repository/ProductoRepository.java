package com.dev.jon.myMenu.repository;

import com.dev.jon.myMenu.entitys.Menu;
import com.dev.jon.myMenu.entitys.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByMenuId(Long menuId);

}