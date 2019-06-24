package com.att.data.configurations;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

/**
 * Data Model
 */

@Getter
@Setter
@Entity
public class ConfigValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int configID;

    @Column(nullable = true)
    private String configName; // 'A, B, C, D' etc

    @Column(nullable = true)
    private String configDate; // 022019 for Feb 2019


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigValue that = (ConfigValue) o;
        return configID == that.configID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(configID);
    }

    @Override
    public String toString() {
        return "ConfigValue{" +
                "configID=" + configID +
                ", configName='" + configName + '\'' +
                ", configDate='" + configDate + '\'' +
                '}';
    }
}





//    public ConfigValue(String name, int id) {
//        this.configId = id;
//        this.configName = name;
//    }

//    public ConfigValue() {
//
//    }

//    public void setConfigName(String name) {
//        this.configName = name;
//    }
//
//    public String getConfigName() {
//        return this.configName;
//    }
//
//    public void setConfigId(int id) {
//        this.configId = id;
//    }
//
//    public int getConfigId() {
//        return this.configId;
//    }
//}
