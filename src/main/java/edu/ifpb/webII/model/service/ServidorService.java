package edu.ifpb.webII.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ifpb.webII.model.Servidor;
import edu.ifpb.webII.repository.ServidorRepository;

@Service
public class ServidorService {
    
    @Autowired
    private ServidorRepository servidorRepository;
    
    @Transactional(readOnly = true)
    public List<Servidor> listarServidor() {
        return servidorRepository.findAll();
    }
    
    @Transactional(readOnly = false)
    public Servidor cadastrarServidor(Servidor servidor) {
        return servidorRepository.save(servidor);
    }
    
    @Transactional(readOnly = false)
    public Servidor atualizarServidor(Servidor servidor) {
        return servidorRepository.save(servidor);
    }

    @Transactional(readOnly = true)
    public Servidor listarServidor(Long codigo) {
        return servidorRepository.findById(codigo).orElse(null);
    }
    
    @Transactional(readOnly = false)
    public String deletarServidor(Servidor servidor) {
        Long codigo = servidor.getCodigo();
        servidorRepository.deleteById(codigo);
        return "Servidor de codigo " + codigo + " deletado com sucesso";
    }
    
    @Transactional(readOnly = false)
    public String deletarServidorporID(Long codigo) {
        servidorRepository.deleteById(codigo);
        return "Servidor de codigo " + codigo + " deletado com sucesso";
    }
}
