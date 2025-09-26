package com.example.systrack2.service;

import com.example.systrack2.domain.Moto;
import com.example.systrack2.domain.Patio;
import com.example.systrack2.domain.Usuario;
import com.example.systrack2.DTO.Request.MotoRequestDTO;
import com.example.systrack2.DTO.Response.MotoResponseDTO;
import com.example.systrack2.repository.MotoRepository;
import com.example.systrack2.repository.PatioRepository;
import com.example.systrack2.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MotoService {

    private final MotoRepository motoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PatioRepository patioRepository;

    public MotoResponseDTO criar(MotoRequestDTO dto) {
        if (motoRepository.findByPlaca(dto.getPlaca()).isPresent()) {
            throw new IllegalArgumentException("Placa já cadastrada");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));

        Moto moto = new Moto();
        moto.setPlaca(dto.getPlaca());
        moto.setModelo(dto.getModelo());
        moto.setUsuario(usuario);
        moto.setPatio(patio);

        moto = motoRepository.save(moto);

        return toResponse(moto);
    }

    public List<MotoResponseDTO> listar() {
        return motoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public MotoResponseDTO buscarPorId(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        return toResponse(moto);
    }

    public MotoResponseDTO atualizar(Long id, MotoRequestDTO dto) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));

        // Se a placa foi alterada, verificar duplicidade
        if (!moto.getPlaca().equals(dto.getPlaca())
                && motoRepository.findByPlaca(dto.getPlaca()).isPresent()) {
            throw new IllegalArgumentException("Placa já cadastrada");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));

        moto.setPlaca(dto.getPlaca());
        moto.setModelo(dto.getModelo());
        moto.setUsuario(usuario);
        moto.setPatio(patio);

        moto = motoRepository.save(moto);
        return toResponse(moto);
    }

    public void excluir(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new EntityNotFoundException("Moto não encontrada");
        }
        motoRepository.deleteById(id);
    }

    private MotoResponseDTO toResponse(Moto moto) {
        MotoResponseDTO dto = new MotoResponseDTO();
        dto.setId(moto.getId());
        dto.setPlaca(moto.getPlaca());
        dto.setModelo(moto.getModelo());
        dto.setUsuarioId(moto.getUsuario() != null ? moto.getUsuario().getId() : null); // ADICIONADO
        dto.setNomeUsuario(moto.getUsuario() != null ? moto.getUsuario().getNome() : null);
        dto.setPatioId(moto.getPatio() != null ? moto.getPatio().getId() : null);       // ADICIONADO
        dto.setNomePatio(moto.getPatio() != null ? moto.getPatio().getNome() : null);
        return dto;
    }
}

