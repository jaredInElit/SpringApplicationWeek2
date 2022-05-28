package com.promineotech.jeep.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.jeep.entity.Jeep;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/jeeps")
@OpenAPIDefinition(info = @Info(title = "Jeep Sales"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface JeepSalesController {
  @Operation(
      summary = "Returns List of Jeeps",
      description = "Returns List of Jeeps given an optional models and trims",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "Returned List of Jeeps", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Jeep.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "Invalid Request Parameters", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Jeeps Found with that criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An Error has Occured", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "model", 
              allowEmptyValue = false, 
              required = false, 
              description = "The model name (i.e., 'WRANGLER')"),
          @Parameter(name = "trim", 
              allowEmptyValue = false, 
              required = false, 
              description = "The trim level (i.e., 'SPORT')")
      }
  )
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Jeep> fetchJeeps(
      @RequestParam(required = false)
          String model,
      @RequestParam(required = false)
          String trim);
}