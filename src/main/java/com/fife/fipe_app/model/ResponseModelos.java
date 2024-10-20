package com.fife.fipe_app.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseModelos(List<DataModelos> modelos, List<DataAnos> anos) {}
