package com.warehouse_accounting.services.impl;

import com.warehouse_accounting.models.ContractorGroup;
import com.warehouse_accounting.models.dto.ContractorGroupDto;
import com.warehouse_accounting.repositories.ContractorGroupRepository;
import com.warehouse_accounting.services.interfaces.ContractorGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContractorGroupServiceImpl implements ContractorGroupService {

    private final ContractorGroupRepository contractorGroupRepository;

    public ContractorGroupServiceImpl(ContractorGroupRepository contractorGroupRepository) {
        this.contractorGroupRepository = contractorGroupRepository;
    }

    @Override
    public List<ContractorGroupDto> getAll() {
        return contractorGroupRepository.getAll();
    }

    @Override
    public ContractorGroupDto getById(Long id) {
        return contractorGroupRepository.getById(id);
    }

    @Override
    public void create(ContractorGroupDto contractorGroupDto) {
        contractorGroupRepository.save(convertToModel(contractorGroupDto));
    }

    @Override
    public void update(ContractorGroupDto contractorGroupDto) {
        contractorGroupRepository.save(convertToModel(contractorGroupDto));
    }

    @Override
    public void deleteById(Long id) {
        contractorGroupRepository.deleteById(id);
    }

    private ContractorGroup convertToModel(ContractorGroupDto contractorGroupDto) {
        return ContractorGroup.builder()
                .id(contractorGroupDto.getId())
                .name(contractorGroupDto.getName())
                .sortNumber(contractorGroupDto.getSortNumber())
                .build();
    }

    private ContractorGroupDto convertToDto(ContractorGroup contractorGroup) {
        return ContractorGroupDto.builder()
                .id(contractorGroup.getId())
                .name(contractorGroup.getName())
                .sortNumber(contractorGroup.getSortNumber())
                .build();
    }

}
