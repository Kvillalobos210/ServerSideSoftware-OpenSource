package com.appdhome.services;

import com.appdhome.entities.District;

public interface IDistrictService extends CrudService<District>{
    public District findByName(String name) throws Exception;
}
