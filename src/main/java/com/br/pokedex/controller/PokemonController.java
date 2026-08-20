package com.br.pokedex.controller;

import com.br.pokedex.entity.PokemonEntity;
import com.br.pokedex.entity.enums.Type;
import com.br.pokedex.service.PokemonService;
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
        name = "Rotas de controle da Pokedex",
        description = "Endpoints de funcionamento da Pokedex"
)
@RestController
@RequestMapping("v1/pokemons")

public class PokemonController
{
    private static List<PokemonEntity> pokedex = new CopyOnWriteArrayList<>();
    private static Integer newId()
    {
        return pokedex.size() + 1;
    }

    /**
     * Lista pokemons da pokedex
     *
     * <p>Retorna uma lista com todos os pokemons cadastrados.</p>
     *
     * @return lista dos pokemons cadastrados
     */
    @Operation(
            summary = "Lista os pokemons",
            description = "Retorna uma lista com os pokemons cadastrados na pokedex"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista encontrada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não encontrado"
            )
    })
    @GetMapping
    public ResponseEntity<List<PokemonEntity>> listPokemon()
    {
        return ResponseEntity.ok(pokedex);
    }

    /**
     * Busca pokemon pelo id
     *
     * <p>Busca pokemon específico na pokedex através do id</p>
     *
     * @param id identificador único do pokemon
     * @return objeto de resposta com a entidade pokemon
     */
    @Operation(
            summary = "Busca pokemon pelo id",
            description = "Busca pokemon com o id requisitado na lista da pokedex"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pokemon encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não encontrado"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<PokemonEntity> findById(
            @Parameter(
                    description = "Identificador único do pokemon",
                    example = "1"
            )
            @PathVariable Integer id)
    {
        return pokedex.stream()
                .filter(pokemon -> pokemon.getId().equals(id))
                .findFirst()
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .orElse(ResponseEntity.notFound().build());
    }


    /**
     * Cadastra novo pokemon
     *
     * @param pokemon objeto entidade de pokemon para ser cadastrado
     * @return objeto de resposta com a entidade pokemon atualizada com o id
     */
    @Operation(
            summary = "Cadastra novo pokemon na pokedex",
            description = "Realiza o cadastro de um novo pokemon inserindo-o na lista pokedex local"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Pokemon cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não foi cadastrado"
            )
    })
    @PostMapping
    public ResponseEntity<PokemonEntity> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do novo pokemon",
                    required = true
            )
            @RequestBody PokemonEntity pokemon
    )
    {
        pokemon.setId(newId());

        pokedex.add(pokemon);

        return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);
    }


    /**
     * Atualiza pokemon pelo id
     *
     * @param id identificador do pokemon
     * @param pokemon dados atualizados do pokemon
     * @return pokemon atualizado
     */

    @Operation(
            summary = "Atualizar Pokemon ",
            description = "Atualiza o okemon pelo seu id e pokemon atualizado informado"
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
    @PutMapping("/{id}")
    public ResponseEntity<PokemonEntity> update(
            @Parameter(
                    description = "Identificador para atualizar",
                    example = "1"
            )
            @PathVariable Integer id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para atualizar o pokemon",
                    required = true
            )
            @RequestBody PokemonEntity pokemon
    )
    {
        Optional<PokemonEntity> pokemonEncontrado =
                pokedex.stream()
                        .filter(pokemon1 -> pokemon1.getId().equals(id))
                        .findFirst();

        if (pokemonEncontrado.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        int indice = pokedex.indexOf(pokemonEncontrado.get());

        pokemon.setId(id);

        pokedex.set(indice, pokemon);

        return ResponseEntity.ok(pokemon);
    }


    /**
     * Deleta Pokemon pelo Id
     *
     * <p>Ele seleciona o id escolhido e deleta ele da pokedex</p>
     *
     * @param id objetivo é deletar o pokemon pelo id
     * @return vai retornar a mensagem de sucesso ou erro
     */
    @Operation(
            summary = "Deletar pokemon da pokedex",
            description = "Remove o pokemon pelo id selecionado da pokedex"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Pokemon Deletado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não foi encontrado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(
            @Parameter(
                    description = "Identificador unico do pokemon",
                    example = "1"
            )
            @PathVariable Integer id)
    {
        Optional<PokemonEntity> pokemonDeletado =
                pokedex.stream()
                        .filter(pokemon -> pokemon.getId().equals(id))
                        .findFirst();

        if (pokemonDeletado.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        int indice = pokedex.indexOf(pokemonDeletado.get());

        pokedex.remove(indice);

        return ResponseEntity.noContent().build();
    }


    /**
     * Busca pokemon pelo tipo
     *
     * <p>Busca o primeiro pokemon encontrado de acordo com o tipo informado</p>
     *
     * @param type tipo do pokemon
     * @return pokemon encontrado
     */

    @Operation(
            summary = "Procura pokemon pelo tipo",
            description = "Procura o pokemon pelo seu tipo informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pokemon Encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não foi encontrado"
            )
    })
    @GetMapping("/tipo")
    public ResponseEntity<PokemonEntity> findByType(
            @RequestParam Type type)
    {
        Optional<PokemonEntity> pokemonEncontrado =
                pokedex.stream()
                        .filter(pokemon -> pokemon.getType() == type)
                        .findFirst();

        if (pokemonEncontrado.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pokemonEncontrado.get());
    }


    /**
     * Busca pokemon pelo nome
     *
     * <p>Busca o primeiro pokemon encontrado de acordo com o nome informado</p>
     *
     * @param name nome do pokemon
     * @return pokemon encontrado
     */

    @Operation(
            summary = "Procura pokemon pelo nome",
            description = "Procura o pokemon pelo seu nome informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pokemon Encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não foi encontrado"
            )
    })
    @GetMapping("/nome")
    public ResponseEntity<PokemonEntity> findByName(
            @RequestParam String name)
    {
        Optional<PokemonEntity> pokemonEncontrado =
                pokedex.stream()
                        .filter(pokemon ->
                                pokemon.getName().equalsIgnoreCase(name))
                        .findFirst();

        if (pokemonEncontrado.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pokemonEncontrado.get());
    }


    /**
     * Busca pokemon pelo nivel
     *
     * <p>Busca o primeiro pokemon encontrado de acordo com o nivel informado</p>
     *
     * @param level nivel do pokemon
     * @return pokemon encontrado
     */

    @Operation(
            summary = "Procura pokemon pelo level",
            description = "Procura o pokemon pelo seu level informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pokemon Encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não foi encontrado"
            )
    })
    @GetMapping("/nivel")
    public ResponseEntity<PokemonEntity> findByLevel(
            @RequestParam Integer level)
    {
        Optional<PokemonEntity> pokemonEncontrado =
                pokedex.stream()
                        .filter(pokemon ->
                                pokemon.getLevel() == level)
                        .findFirst();

        if (pokemonEncontrado.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pokemonEncontrado.get());
    }


    /**
     * Atualiza somente o nivel do pokemon
     *
     * <p>Altera somente o nivel do pokemon através do id informado</p>
     *
     * @param id identificador do pokemon
     * @param level novo nivel do pokemon
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
    @PatchMapping("/{id}/nivel")
    public ResponseEntity<PokemonEntity> updateLevel(
            @PathVariable Integer id,
            @RequestParam Integer level)
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

        pokemon.setLevel(level);

        return ResponseEntity.ok(pokemon);
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
    @PatchMapping("/{id}/curar")
    public ResponseEntity<PokemonEntity> healPokemon(
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
    @PatchMapping("/{id}/aumentar-nivel")
    public ResponseEntity<PokemonEntity> aumentarNivelPokemon(
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


    /**
     * Busca o pokemon mais forte
     *
     * <p>Retorna o pokemon que possui o maior ataque</p>
     *
     * @return pokemon com maior ataque
     */

    @Operation(
            summary = "Localizar o pokemon mais forte ",
            description = "Localiza o pokemon mais forte de acordo com o seu nivel"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pokemon Encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pokemon não foi encontrado"
            )
    })
    @GetMapping("/mais-forte")
    public ResponseEntity<PokemonEntity> findStrongest()
    {
        Optional<PokemonEntity> pokemonEncontrado =
                pokedex.stream()
                        .max((pokemon1, pokemon2) ->
                                Integer.compare(
                                        pokemon1.getLevel(),
                                        pokemon2.getLevel()
                                ));

        if (pokemonEncontrado.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pokemonEncontrado.get());
    }
}