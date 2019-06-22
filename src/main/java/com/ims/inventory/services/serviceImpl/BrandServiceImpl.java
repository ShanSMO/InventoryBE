package com.ims.inventory.services.serviceImpl;

import com.google.common.collect.Lists;
import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.dtos.SearchResponseDto;
import com.ims.inventory.models.Brand;
import com.ims.inventory.models.QBrand;
import com.ims.inventory.repositories.BrandRepository;
import com.ims.inventory.services.BrandService;
import com.ims.inventory.utils.Consts;
import com.ims.inventory.utils.PagingSortingAndOrdering;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    QBrand qBrand = QBrand.brand;

    private static Integer RECORDS_PER_PAGE = 10;

    @Override
    public ResponseDto createUpdate(Brand brand) {
        ResponseDto responseDto = new ResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (brand.getBrandName() != null) {
            booleanBuilder.and(qBrand.brandName.equalsIgnoreCase(brand.getBrandName().trim()));
            Optional<Brand> brandOpt = brandRepository.findOne(booleanBuilder);
            if (brandOpt.isPresent()) {
                responseDto.setStatus(Consts.JOB_FAILED);
                responseDto.setMessage("Brand already exists");
            } else {
                brand.setBrandName(brand.getBrandName().trim());
                responseDto.setResponseObject(brandRepository.saveAndFlush(brand));
                responseDto.setStatus(Consts.JOB_SUCCESS);
                responseDto.setMessage("Successfully added");
            }
        } else {
            responseDto.setStatus(Consts.JOB_FAILED);
            responseDto.setMessage("Invalid request");
        }

        return responseDto;
    }

    @Override
    public ResponseDto loadAll() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseItems(brandRepository.findAll());
        return responseDto;
    }

    @Override
    public SearchResponseDto loadAll(Brand brand) {
        SearchResponseDto responseDto = new SearchResponseDto();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (brand.getBrandName() != null) {
            booleanBuilder.and(qBrand.brandName.contains(brand.getBrandName()));
        }

        List<Brand> brandList = Lists.newArrayList(brandRepository.findAll(booleanBuilder,PagingSortingAndOrdering.createPageRequest(
                brand.getCurrentPage(),RECORDS_PER_PAGE,brand.getSortingField(),brand.getSortingDirection())));

        responseDto.setRecordCount(brandRepository.count());

        responseDto.setSearchItems(brandList);

        return responseDto;
    }
}
