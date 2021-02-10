package com.warehouse_accounting.controllers.rest;

import com.warehouse_accounting.models.dto.TaxSystemDto;
import com.warehouse_accounting.services.interfaces.TaxSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api( tags = {"TaxSystem"})
@RequestMapping("/api/tax_systems")
public class TaxSystemController {
    private final TaxSystemService taxSystemService;

    public TaxSystemController(TaxSystemService taxSystemService) {
        this.taxSystemService = taxSystemService;
    }

    @GetMapping
    @ApiOperation(value = "Возвращает все системы налогообложения", notes = "Возвращает список TaxSystemDto",
            response = TaxSystemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение листа систем налогообложения",
                    response = TaxSystemDto.class),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public List<TaxSystemDto> getAll() {
        return taxSystemService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Возвращает систему налогообложения с выбранным id", notes = "Возвращает TaxSystemDto",
            response = TaxSystemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение системы налогообложения",
                    response = TaxSystemDto.class),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public TaxSystemDto getById(@ApiParam(name = "id", value = "Id of TaxSystemDto", required = true)
                                @PathVariable("id") Long id) {
        return taxSystemService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "Обновляет переданную систему налогообложения", notes = "Ничего не возвращает",
            response = TaxSystemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное обновление системы налогообложения",
                    response = TaxSystemDto.class),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public void update(@ApiParam(name = "TaxSystemDto", value = "object of TaxSystemDto for update", required = true)
                       @RequestBody TaxSystemDto taxSystemDto) {
        taxSystemService.update(taxSystemDto);
    }

    @PostMapping
    @ApiOperation(value = "Создает переданную систему налогообложения", notes = "Ничего не возвращает",
            response = TaxSystemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное создание системы налогообложения",
                    response = TaxSystemDto.class),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public void create(@ApiParam(name = "TaxSystemDto", value = "object of TaxSystemDto for create", required = true)
                       @RequestBody TaxSystemDto taxSystemDto) {
        taxSystemService.create(taxSystemDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удаляет систему налогообложения с выбранным id",
            notes = "Ничего не возвращает", response = TaxSystemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное удаление системы налогообложения",
                    response = TaxSystemDto.class),
            @ApiResponse(code = 404, message = "Данный контролер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public void deleteById(@ApiParam(name = "id", value = "Id of TaxSystemDto for delete", required = true)
                           @PathVariable("id") Long id) {
        taxSystemService.deleteById(id);
    }
}
