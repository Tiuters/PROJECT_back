package com.warehouse_accounting.services.interfaces;

import com.warehouse_accounting.models.dto.TaxSystemDto;

import java.util.List;

public interface TaxSystemService {

    List<TaxSystemDto> getAll();

    TaxSystemDto getById(Long id);

    void create(TaxSystemDto dto);

    void update(TaxSystemDto dto);

    void deleteById(Long id);
}
