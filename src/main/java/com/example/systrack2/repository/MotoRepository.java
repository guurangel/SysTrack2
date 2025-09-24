package com.example.systrack2.repository;

import com.example.systrack2.domain.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    Optional<Moto> findByPlaca(String placa);
}
