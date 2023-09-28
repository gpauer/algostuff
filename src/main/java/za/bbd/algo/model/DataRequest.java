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
public class DataRequest {
    private String algo;
    private ArrayList<String> data;
}