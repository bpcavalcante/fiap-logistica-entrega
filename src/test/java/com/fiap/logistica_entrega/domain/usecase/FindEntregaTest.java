package com.fiap.logistica_entrega.domain.usecase;

import com.fiap.logistica_entrega.domain.ports.outbound.EntregaRepositoryPort;
import com.fiap.logistica_entrega.domain.ports.outbound.dto.EntregaDatabaseDto;
import com.fiap.logistica_entrega.domain.ports.inbound.dto.EntregaDto;
import com.fiap.logistica_entrega.domain.exceptions.DatabaseErrorException;
import com.fiap.logistica_entrega.domain.exceptions.ResourceNotFoundException;
import com.fiap.logistica_entrega.infrastructure.exceptions.ResourceNotFoundDatabaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindEntregaTest {

    @Mock
    private EntregaRepositoryPort entregaRepositoryPort;

    @InjectMocks
    private FindEntrega findEntrega;

    private EntregaDatabaseDto entregaDatabaseDto;
    private long validId;
    private String validCep;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validId = 1L;
        validCep = "12345-678";
        entregaDatabaseDto = EntregaDatabaseDto.builder().idEntrega(validId).cep(validCep).build();
    }

    @Test
    public void testFindByIdSuccess() {
        // Configura o comportamento do mock
        when(entregaRepositoryPort.findById(validId)).thenReturn(entregaDatabaseDto);

        // Chama o método a ser testado
        EntregaDto result = findEntrega.findById(validId);

        // Verifica se o método retorna o DTO corretamente
        assertNotNull(result);
        assertEquals(validId, result.getIdEntrega());

        // Verifica se o método de busca foi chamado corretamente no repositório
        verify(entregaRepositoryPort).findById(validId);
    }

    @Test
    public void testFindByIdNotFound() {
        // Configura o comportamento do mock para lançar uma exceção
        when(entregaRepositoryPort.findById(validId)).thenThrow(new ResourceNotFoundDatabaseException("Entrega not found"));

        // Chama o método e verifica se a exceção é lançada corretamente
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> findEntrega.findById(validId));
        assertEquals("Entrega not found", exception.getMessage());

        // Verifica se o método de busca foi chamado corretamente no repositório
        verify(entregaRepositoryPort).findById(validId);
    }

    @Test
    public void testFindAllSuccess() {
        // Configura o comportamento do mock
        when(entregaRepositoryPort.findAll()).thenReturn(Arrays.asList(entregaDatabaseDto));

        // Chama o método a ser testado
        List<EntregaDto> result = findEntrega.findAll();

        // Verifica se a lista não está vazia e contém o DTO correto
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(validId, result.get(0).getIdEntrega());

        // Verifica se o método de busca foi chamado corretamente no repositório
        verify(entregaRepositoryPort).findAll();
    }

    @Test
    public void testFindAllEmptyList() {
        // Configura o comportamento do mock para retornar uma lista vazia
        when(entregaRepositoryPort.findAll()).thenReturn(Collections.emptyList());

        // Chama o método a ser testado
        List<EntregaDto> result = findEntrega.findAll();

        // Verifica se a lista retornada é vazia
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verifica se o método de busca foi chamado corretamente no repositório
        verify(entregaRepositoryPort).findAll();
    }

    @Test
    public void testFindByCepSuccess() {
        // Configura o comportamento do mock
        when(entregaRepositoryPort.findByCep(validCep)).thenReturn(Arrays.asList(entregaDatabaseDto));

        // Chama o método a ser testado
        List<EntregaDto> result = findEntrega.findByCep(validCep);

        // Verifica se a lista não está vazia e contém o DTO correto
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(validCep, result.get(0).getCep());

        // Verifica se o método de busca foi chamado corretamente no repositório
        verify(entregaRepositoryPort).findByCep(validCep);
    }

    @Test
    public void testFindByCepEmptyList() {
        // Configura o comportamento do mock para retornar uma lista vazia
        when(entregaRepositoryPort.findByCep(validCep)).thenReturn(Collections.emptyList());

        // Chama o método a ser testado
        List<EntregaDto> result = findEntrega.findByCep(validCep);

        // Verifica se a lista retornada é vazia
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verifica se o método de busca foi chamado corretamente no repositório
        verify(entregaRepositoryPort).findByCep(validCep);
    }

    @Test
    public void testFindByCepError() {
        // Configura o comportamento do mock para lançar uma exceção
        when(entregaRepositoryPort.findByCep(validCep)).thenThrow(new RuntimeException("Database error"));

        // Chama o método e verifica se a exceção DatabaseErrorException é lançada corretamente
        DatabaseErrorException exception = assertThrows(DatabaseErrorException.class, () -> findEntrega.findByCep(validCep));
        assertEquals("Error while fetching delivery by CEP: Database error", exception.getMessage());

        // Verifica se o método de busca foi chamado corretamente no repositório
        verify(entregaRepositoryPort).findByCep(validCep);
    }
}
