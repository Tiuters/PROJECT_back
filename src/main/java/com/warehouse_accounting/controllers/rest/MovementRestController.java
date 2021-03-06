package com.warehouse_accounting.controllers.rest;

import com.warehouse_accounting.models.dto.MovementDto;
import com.warehouse_accounting.repositories.MovementRepository;
import com.warehouse_accounting.services.utilServices.ExportXlsxSpreadsheetService;
import com.warehouse_accounting.services.interfaces.CheckEntityService;
import com.warehouse_accounting.services.interfaces.MovementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/movements")
@Api(tags = "Movement Rest Controller")
@Tag(name = "Movement Rest Controller", description = "CRUD операции с объектами")
public class MovementRestController {

    private final MovementService service;
    private final CheckEntityService checkEntityService;
    private final ExportXlsxSpreadsheetService exportXlsxFileService;
    private final MovementRepository repository;

    public MovementRestController(MovementService service, CheckEntityService checkEntityService,
                                  ExportXlsxSpreadsheetService exportXlsxFileService, MovementRepository repository) {
        this.service = service;
        this.checkEntityService = checkEntityService;
        this.exportXlsxFileService = exportXlsxFileService;
        this.repository = repository;
    }

    @GetMapping
    @ApiOperation(value = "Возвращает список MovementDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка MovementDto"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public ResponseEntity<List<MovementDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Возвращает MovementDto по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение MovementDto"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public ResponseEntity<MovementDto> getById(@ApiParam(name = "id", value = "id для получения MovementDto",
            required = true) @PathVariable("id") Long id) {
        checkEntityService.checkExist(id, repository, "Movement");
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "Создает Movement")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное создание Movement"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public ResponseEntity<?> create(@ApiParam(name = "MovementDto", value = "объект MovementDto для создания",
            required = true) @RequestBody MovementDto movementDto) {
        service.create(movementDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ApiOperation(value = "Обновляет Movement")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное обновление Movement"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public ResponseEntity<?> update(@ApiParam(name = "MovementDto", value = "объект MovementDto для обновления",
            required = true) @RequestBody MovementDto movementDto) {
        checkEntityService.checkExist(movementDto.getId(), repository, "Movement");
        service.update(movementDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удаляет Movement по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное удаление Movement"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public ResponseEntity<?> deleteById(@ApiParam(name = "id", value = "id для удаления Movement",
            required = true) @PathVariable("id") Long id) {
        checkEntityService.checkExist(id, repository, "Movement");
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/export/xlsx")
    @ApiOperation(value = "Скачать таблицу со списком MovementDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение таблицы со списком MovementDto"),
            @ApiResponse(code = 404, message = "Данный контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к данной операции")})
    public ResponseEntity<Resource> getExcel() {
        String filename = "some.xlsx";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(exportXlsxFileService.getWorkbook(MovementDto.class));
    }

}
