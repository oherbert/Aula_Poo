package com.example.demo.repository;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import com.example.demo.entities.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepository{

    List<Cliente> clientes = new ArrayList<>();

@PostConstruct
public void init(){
    
    clientes.add(new Cliente(1,"José","123.456.789-10"));
    clientes.add(new Cliente(2,"Maria","987.654.321-01"));
    clientes.add(new Cliente(3,"Pedro","741.852.963-12"));
    clientes.add(new Cliente(4,"Antônio","369.258.147-45"));
    clientes.add(new Cliente(5,"Pelé","111.222.333-44"));   
}

public Cliente saveCliente(Cliente cliente){

    Cliente newCliente = new Cliente((clientes.size()+1),cliente.getNome(),cliente.getCpf());
    clientes.add(newCliente);
    System.out.println(newCliente);
    return (newCliente);
}

public List<Cliente> getClients(){
    return clientes;
}

public Cliente getClienteById(int id){
    Cliente cli = null;

    for(Cliente cliente: clientes){
        if (cliente.getId() == id){
            cli = cliente;
        }       
    }
    
    return cli;
}


}