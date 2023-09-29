package za.bbd.algo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataRequest {
    private String algo;
    private ArrayList<Integer> data;
}