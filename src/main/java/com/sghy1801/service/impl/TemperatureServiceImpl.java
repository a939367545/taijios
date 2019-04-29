package com.sghy1801.service.impl;

import com.sghy1801.dao.TemperatureMapper;
import com.sghy1801.entity.Temperature;
import com.sghy1801.service.TemperatureService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TemperatureServiceImpl implements TemperatureService {
    @Autowired
    private TemperatureMapper temperatureMapper;

    @Override
    public List<Map> getHoursTemperature(int machineID) {
        Map<String,Object> map = new HashMap<String,Object>();
        Date date = new Date();
        map.put("machineID",machineID);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newtime = sdf.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        String oldtiem = sdf.format(calendar.getTime());
        map.put("oldtime","2019-4-14 00:00:00");
        map.put("newtime","2019-4-15 00:00:00");
//        map.put("oldtime",oldtiem+" 00:00:00");
//        map.put("newtime",newtime+" 00:00:00");
        return temperatureMapper.getHoursTemperature(map);
    }

    @Override
    public List<Map> getDaysTemperature(Map<String, Object> map) {
        return temperatureMapper.getDaysTemperature(map);
    }

    @Override
    public int addTemperature(Temperature temperature) {
        return temperatureMapper.addTemperature(temperature);
    }

    @Override
    public int delTemperatureByTime(Map<String, Object> map) {
        return temperatureMapper.delTemperatureByTime(map);
    }

    @Override
    public Temperature getLastTemperature(int machineID) {
        return temperatureMapper.getLastTemperature(machineID);
    }
}
