package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import com.example.demo.entities.Cliente;
import com.example.demo.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/Cliente")

@RestController
public class ClienteController {
    
    @Autowired
    ClienteRepository clientes;

@GetMapping("/Clientes")
public List<Cliente> getClients(){
    return clientes.getClients();
}

@GetMapping("/{id}")
public ResponseEntity<Cliente> getCliente(@PathVariable int id) {
    Cliente cliente = clientes.getClienteById(id);

    if ( cliente == null){
        return ResponseEntity.notFound().build();
    }else{
        return ResponseEntity.ok(cliente);
    }
}

@PostMapping()
public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente){
    cliente = clientes.saveCliente(cliente);
    URI uri = URI.create("http://localhost:8080/Clientes/"+cliente.getId());
        
    return ResponseEntity.created(uri).build();
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> remover(@PathVariable int id){

    Cliente cliente = clientes.getClienteById(id);

    if(cliente == null){
        return ResponseEntity.notFound().build();
    }else{
        clientes.delete(cliente);
        return ResponseEntity.noContent().build();
    }
}

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente, @PathVariable int id){
        Cliente cli = clientes.getClienteById(id);

        if (cli != null){
            cliente.setId(id);
            cli = clientes.update(cliente);
            return ResponseEntity.ok(cli);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}