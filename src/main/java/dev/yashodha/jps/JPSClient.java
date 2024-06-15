package dev.yashodha.jps;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JPSClient <T>{
    List<T> findAll();

    T findById(Integer id);

    T create(T t);

    T update(Integer id, T t);

    ResponseEntity<Void> delete(Integer id);
}
