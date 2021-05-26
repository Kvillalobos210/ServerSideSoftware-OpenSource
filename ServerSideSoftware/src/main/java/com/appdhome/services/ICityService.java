package com.appdhome.services;

import com.appdhome.entities.City;
import com.appdhome.entities.District;

public interface ICityService extends CrudService<City>{
    public City findByName(String name) throws Exception;
}
