package com.br.pokedex.controller;

import com.br.pokedex.entity.PokemonEntity;
import com.br.pokedex.entity.TreinadorEntity;
import com.br.pokedex.entity.enums.Type;
import com.br.pokedex.service.TreinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Tag(
        name = "Controller de Treinador",
        description = "Endpoints de funcionamento do treinador"
)
@RestController
@RequestMapping("v1/treinador")
public class TreinadorController
{
    private final TreinadorService treinadorService = new TreinadorService();
    private static List<PokemonEntity> pokedex = new ArrayList<>();
    TreinadorEntity treinador = new TreinadorEntity(1,"Nogath Pobre",null,null);
    public TreinadorController()
    {
        pokedex.add(new PokemonEntity(
                "Pikachu",
                "Um Pokémon do tipo elétrico que armazena energia em suas bochechas.",
                25,
                PokemonEntity.State.NORMAL,
                48,
                55,
                false,
                Type.ELECTRIC,
                2

        ));

        pokedex.add(new PokemonEntity(
                "Charmander",
                "Um Pokémon do tipo fogo cuja chama na ponta da cauda indica sua condição física.",
                12,
                PokemonEntity.State.NORMAL,
                39,
                45,
                false,
                Type.FIRE,
                1
        ));

        pokedex.add(new PokemonEntity(
                "Bulbasaur",
                "Um Pokémon do tipo planta que nasce com uma semente nas costas.",
                10,
                PokemonEntity.State.NORMAL,
                42,
                45,
                false,
                Type.GRASS,
                1
        ));

        pokedex.add(new PokemonEntity(
                "Squirtle",
                "Um Pokémon do tipo água que possui um casco resistente e pode se esconder dentro dele.",
                15,
                PokemonEntity.State.NORMAL,
                41,
                44,
                false,
                Type.WATER,
                1
        ));

        pokedex.add(new PokemonEntity(
                "Eevee",
                "Um Pokémon com uma estrutura genética instável que permite diferentes formas de evolução.",
                18,
                PokemonEntity.State.NORMAL,
                46,
                55,
                true,
                Type.NORMAL,
                1
        ));

        pokedex.add(new PokemonEntity(
                "Vulpix",
                "Um Pokémon do tipo fogo conhecido por sua bela cauda e por controlar pequenas chamas.",
                14,
                PokemonEntity.State.NORMAL,
                38,
                40,
                false,
                Type.FIRE,
                1
        ));

        pokedex.add(new PokemonEntity(
                "Geodude",
                "Um Pokémon rochoso que vive principalmente em áreas montanhosas e cavernas.",
                16,
                PokemonEntity.State.NORMAL,
                50,
                55,
                false,
                Type.FIGHTING,
                1
        ));

        pokedex.add(new PokemonEntity(
                "Gastly",
                "Um Pokémon fantasma formado principalmente por uma substância gasosa.",
                20,
                PokemonEntity.State.NORMAL,
                32,
                35,
                false,
                Type.PSYCHIC,
                1
        ));

        pokedex.add(new PokemonEntity(
                "Jigglypuff",
                "Um Pokémon que pode cantar uma melodia capaz de fazer seus adversários dormirem.",
                22,
                PokemonEntity.State.NORMAL,
                50,
                60,
                false,
                Type.NORMAL,
                1
        ));

        pokedex.add(new PokemonEntity(
                "Oddish",
                "Um Pokémon do tipo planta que costuma aparecer durante a noite e se movimentar enquanto procura nutrientes.",
                11,
                PokemonEntity.State.NORMAL,
                35,
                40,
                false,
                Type.GRASS,
                1
        ));
    }
    /**
     * Atualiza pokemon pelo id
     *
     *
     * @param name dados atualizados do pokemon
     * @return pokemon atualizado
     */

    @Operation(
            summary = "Atualizar Pokemon ",
            description = "Atualiza o pokemon pelo seu id e pokemon atualizado informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pokemon Atualizado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não foi encontrado"
            )
    })

    @PatchMapping("/{id}/name")
    public ResponseEntity<TreinadorEntity> updateName(
            @Parameter(
                    description = "Identificador para atualizar",
                    example = "1"
            )
            @PathVariable String name
    )
    {
        if(name.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        TreinadorEntity treinadorAtualizado = treinadorService.updateNome(treinador,name);

        return ResponseEntity.ok(treinadorAtualizado);
    }



    /**
     * Atualiza somente o nivel do pokemon
     *
     * <p>Altera somente o nivel do pokemon através do id informado</p>
     *
     * @param id identificador do pokemon
     * @param treinador novo nivel do pokemon
     * @return pokemon com o nivel atualizado
     */

    @Operation(
            summary = "Atualizar level ",
            description = "Atualiza o level pelo seu id  e level informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pokemon Subiu de Level"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não foi encontrado"
            )
    })

    @PatchMapping("/{id}")
    public ResponseEntity<TreinadorEntity> capturePokemon(
            @PathVariable Integer id,
            @RequestParam TreinadorEntity treinador)
    {
        TreinadorEntity treinadorAtualizado = treinadorService.capturar(treinador,pokedex);

        return ResponseEntity.ok(treinadorAtualizado);
    }


    /**
     * Cura o pokemon
     *
     * <p>Restaura a vida do pokemon</p>
     *
     * @param id identificador do pokemon
     * @return pokemon com a vida restaurada
     */

    @Operation(
            summary = "Curar pokemon ",
            description = "Cura o pokemon pelo id informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pokemon Curado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não foi encontrado"
            )
    })
    @PostMapping("/{id}/curar")
    public ResponseEntity<PokemonEntity> useItem(
            @PathVariable Integer id)
    {
        Optional<PokemonEntity> pokemonEncontrado =
                pokedex.stream()
                        .filter(pokemon -> pokemon.getId().equals(id))
                        .findFirst();

        if (pokemonEncontrado.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        PokemonEntity pokemon = pokemonEncontrado.get();

        pokemon.setActualPs(pokemon.getMaxPs());

        return ResponseEntity.ok(pokemon);
    }


    /**
     * Evolui o pokemon
     *
     * <p>Aumenta o nivel do pokemon</p>
     *
     * @param id identificador do pokemon
     * @return pokemon evoluido
     */

    @Operation(
            summary = "Evoluir pokemon ",
            description = "Evolui o pokemon pelo id infromado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pokemon Evoluido"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não foi encontrado"
            )
    })
    @PostMapping("/{id}/evoluir")
    public ResponseEntity<PokemonEntity> evolvePokemon(
            @PathVariable Integer id)
    {
        Optional<PokemonEntity> pokemonEncontrado =
                pokedex.stream()
                        .filter(pokemon -> pokemon.getId().equals(id))
                        .findFirst();

        if (pokemonEncontrado.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        PokemonEntity pokemon = pokemonEncontrado.get();

        pokemon.setLevel(pokemon.getLevel() + 1);

        return ResponseEntity.ok(pokemon);
    }

}
