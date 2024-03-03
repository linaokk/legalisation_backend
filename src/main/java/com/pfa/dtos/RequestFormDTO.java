package com.pfa.dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestFormDTO  implements Serializable {

    private MultipartFile document;
    private String documentType;
    private String description;

}
