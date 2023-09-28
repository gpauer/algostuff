package za.bbd.algo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

import java.util.ArrayList;

@Data
@Getter
@Setter
@NoArgsConstructor
public class DataResponse {
    private String algo;
    private String complexity;
    private long timeInMillis;
    private ArrayList<String> data;
}