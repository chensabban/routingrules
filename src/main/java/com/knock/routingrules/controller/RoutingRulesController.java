package com.knock.routingrules.controller;

import com.knock.routingrules.dto.CreateRoutingRulesDto;
import com.knock.routingrules.dto.RoutingRequestDto;
import com.knock.routingrules.dto.RoutingResponseDto;
import com.knock.routingrules.dto.response.RoutingRulesResponseDto;
import com.knock.routingrules.exception.ErrorResponse;
import com.knock.routingrules.model.RoutingRules;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Tag(name = "Routing Rules", description = "API for managing routing rules and calculating member assignments")
@RequestMapping(path = "/1.0", produces = MediaType.APPLICATION_JSON_VALUE)
public interface RoutingRulesController {

    @Operation(summary = "Create new routing rules")
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Routing rules created successfully",
                content = @Content(schema = @Schema(implementation = RoutingRulesResponseDto.class))),
        @ApiResponse(
                responseCode = "400",
                description = "Invalid input data",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/rules")
    @ResponseStatus(HttpStatus.CREATED)
    RoutingRulesResponseDto createRoutingRules(@Valid @RequestBody CreateRoutingRulesDto createRoutingRulesDto);

    @Operation(summary = "Update existing routing rules")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Routing rules updated successfully",
                content = @Content(schema = @Schema(implementation = RoutingRulesResponseDto.class))),
        @ApiResponse(
                responseCode = "404",
                description = "Routing rules not found",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
                responseCode = "400",
                description = "Invalid input data",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    RoutingRulesResponseDto updateRoutingRules(@PathVariable String id, @Valid @RequestBody CreateRoutingRulesDto createRoutingRulesDto);

    @Operation(summary = "Get routing rules by ID")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Routing rules found",
                content = @Content(schema = @Schema(implementation = RoutingRulesResponseDto.class))),
        @ApiResponse(
                responseCode = "404",
                description = "Routing rules not found",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{id}")
    RoutingRulesResponseDto getRoutingRulesById(@PathVariable @NotEmpty String id);

    @Operation(summary = "Get all routing rules")
    @ApiResponse(
            responseCode = "200",
            description = "List of routing rules",
            content = @Content(schema = @Schema(implementation = RoutingRules.class)))
    @GetMapping
    List<RoutingRulesResponseDto> getAllRoutingRules();

    @Operation(summary = "Calculate member assignment based on contact information and routing rules")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Member assignment calculated successfully",
                content = @Content(schema = @Schema(implementation = RoutingResponseDto.class))),
        @ApiResponse(
                responseCode = "404",
                description = "Routing rules not found",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
                responseCode = "400",
                description = "Invalid contact data",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/calculate")
    RoutingResponseDto calculateRouting(@Valid @RequestBody RoutingRequestDto request);
}