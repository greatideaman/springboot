package com.att.data.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigValue {
    private String configName;
    private int configId;
}
