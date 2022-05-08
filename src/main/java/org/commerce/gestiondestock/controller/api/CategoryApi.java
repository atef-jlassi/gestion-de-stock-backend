package org.commerce.gestiondestock.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.commerce.gestiondestock.dto.ArticleDto;
import org.commerce.gestiondestock.dto.CategoryDto;

import java.util.List;

import static org.commerce.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface CategoryApi {


    @ApiOperation(value = "Enregistrer une categorie",
            notes = "Cette methode permet d'enregistrer ou modifier une categorie", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet categorie n'est pas valide")
    })
    CategoryDto save(CategoryDto dto);

    @ApiOperation(value = "Rechercher une categorie",
            notes = "Cette methode permet de cherche une categorie par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun categorie n'existe dans la BDD avec l'ID fourni")
    })
    CategoryDto findById(Integer id);

    @ApiOperation(value = "Rechercher une categorie",
            notes = "Cette methode permet de cherche une categorie par son CODE", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie n'existent dans la BDD avec le CODE fourni")
    })
    CategoryDto findByCode(String code);

    @ApiOperation(value = "Renvoi la liste des articles ",
            notes = "Cette methode permet de chercher et renvoyer la liste des categories qui existe dans la BDD", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / une liste vide")
    })
    List<CategoryDto> findAll();

    @ApiOperation(value = "Supprimer une categorie", notes = "Cette methode permet de supprimer une categorie par ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a été supprimer")
    })
    void delete(Integer id);

}
