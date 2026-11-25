package com.baozi.store.exception;

import java.time.LocalDateTime;

public record ErroResposta(LocalDateTime timestamp, int status, String erro, String mensagem) {
}