package com.br.pokedex.controller;

import com.br.pokedex.entity.PokemonEntity;
import com.br.pokedex.entity.enums.Type;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Tag(
        name = "Controller de Arena",
        description = "Endpoints de funcionamento da Arena"
)
@RestController
@RequestMapping("v1/arenas")
public class ArenaController
{
    private static List<PokemonEntity> pokedex = new CopyOnWriteArrayList<>();

    private static Integer newId()
    {
        return pokedex.size() + 1;
    }

}
