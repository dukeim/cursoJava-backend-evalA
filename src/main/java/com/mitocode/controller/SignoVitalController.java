package com.mitocode.controller;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Medico;
import com.mitocode.model.Paciente;
import com.mitocode.model.SignoVital;
import com.mitocode.service.ISignoVitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/signosvitales")
public class SignoVitalController {
    @Autowired
    private ISignoVitalService service;

    @GetMapping
    public ResponseEntity<List<SignoVital>> listar() throws Exception {
        List<SignoVital> lista = service.listar();
        return new ResponseEntity<List<SignoVital>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignoVital> listarPorId(@PathVariable("id") Integer id) throws Exception {
        SignoVital obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }
        return new ResponseEntity<SignoVital>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Medico> registrar(@Valid @RequestBody SignoVital p) throws Exception {
        SignoVital obj = service.registrar(p);

        // localhost:8080/signosvitales/2
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdSignoVital()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<SignoVital> modificar(@Valid @RequestBody SignoVital p) throws Exception {
        SignoVital obj = service.modificar(p);
        return new ResponseEntity<SignoVital>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        SignoVital obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        service.eliminar(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<SignoVital>> listarPageable(Pageable pageable) throws Exception{
        Page<SignoVital> signosVitales = service.listarPageable(pageable);
        return new ResponseEntity<Page<SignoVital>>(signosVitales, HttpStatus.OK);
    }
}
