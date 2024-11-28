package com.senac.ms.Email.dtos;

import java.util.UUID;

public record EmailRecordDTO(UUID userId, String emailTo, String subject, String text) {
}
