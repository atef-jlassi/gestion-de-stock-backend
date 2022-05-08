package org.commerce.gestiondestock.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.commerce.gestiondestock.dto.ArticleDto;
import java.util.List;
import static org.commerce.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/articles")
public interface ArticleApi {

    @ApiOperation(value = "Enregistrer un article",
                  notes = "Cette methode permet d'enregistrer ou modifiier un article", response = ArticleDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "L'objet article cree / modifie"),
        @ApiResponse(code = 400, message = "L'objet article n'est pas valide")
    })
    ArticleDto save(ArticleDto articleDto);

    @ApiOperation(value = "Rechercher un article",
            notes = "Cette methode permet de cherche un article par son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'est pas existe  dans la BDD avec l'ID fourni")
    })
    ArticleDto findById(Integer id);

    @ApiOperation(value = "Rechercher un article",
            notes = "Cette methode permet de cherche un article par son CODE", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'est pas existe dans la BDD avec le CODE fourni")
    })
    ArticleDto findByCodeArticle(String codeArticle);


    @ApiOperation(value = "Renvoi la liste des articles ",
            notes = "Cette methode permet de chercher et renvoyer la liste des articles qui existe dans la BDD", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des article / une liste vide")
    })
    List<ArticleDto> findAll();


    @ApiOperation(value = "Supprimer un article", notes = "Cette methode permet de supprimer un article par ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a ete supprimer")
    })
    void delete(Integer id);
}
