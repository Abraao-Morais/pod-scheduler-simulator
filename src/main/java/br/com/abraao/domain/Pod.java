package br.com.abraao.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pod {

    private String name;
    private int cpuRequired;
    private int diskRequired;
    private int memoryRequired;
}
