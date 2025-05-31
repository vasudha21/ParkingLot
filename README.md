# Parking Lot System

## Overview
This is a parking lot management system that handles different types of vehicles, parking spots, and payment processing. The system is designed with extensibility and maintainability in mind, following SOLID principles and various design patterns.

## System Architecture

```mermaid
graph TD
    A[Client<br>Web/Mobile] -->|HTTP/HTTPS| B[API Gateway<br>Auth, Rate-Limit]
    
    B -->|Routes| C[FindParkingSpotAPI]
    B -->|Routes| D[GetTicketAPI]
    B -->|Routes| E[PayParkingFeesAPI]
    B -->|Routes| F[VacateParkingSpotAPI]
    
    C -->|Uses| G[ParkingSpotFinder<br>Strategy Pattern]
    G -->|Uses| H[VehicleParkingManager<br>Interface]
    G -->|Uses| I[ParkingSpotSelector<br>Strategy Pattern]
    
    D -->|Uses| J[TicketGenerator<br>Single Responsibility]
    J -->|Creates| K[Ticket<br>Data Model]
    
    E -->|Uses| L[ParkingFeeProcessor<br>Single Responsibility]
    L -->|Processes| M[PaymentProcessor<br>Interface]
    
    F -->|Uses| N[ParkingSpotVacator<br>Single Responsibility]
    
    subgraph Vehicle_Management
        H -->|Implements| O[TwoWheelerManager<br>Strategy Pattern]
        H -->|Implements| P[FourWheelerManager<br>Strategy Pattern]
        H -->|Implements| Q[HeavyWheelerManager<br>Strategy Pattern]
    end
    
    subgraph Spot_Selection
        I -->|Implements| R[NearestSelector<br>Strategy Pattern]
        I -->|Implements| S[RandomSpotSelector<br>Strategy Pattern]
    end
    
    subgraph Data_Models
        K -->|Contains| T[Vehicle<br>Data Model]
        K -->|Contains| U[ParkingSpot<br>Data Model]
    end
    
    subgraph Factory_Components
        V[VehicleTypeManagerFactory<br>Factory Pattern] -->|Creates| H
        W[SpotSelectorFactory<br>Factory Pattern] -->|Creates| I
    end

    classDef strategy fill:#f9f,stroke:#333,stroke-width:2px
    classDef factory fill:#bbf,stroke:#333,stroke-width:2px
    classDef srp fill:#bfb,stroke:#333,stroke-width:2px
    classDef interface fill:#fbb,stroke:#333,stroke-width:2px

    class G,I,O,P,Q,R,S strategy
    class V,W factory
    class J,L,N srp
    class H,M interface
```

*Color coding indicates different design patterns:*
- *Pink: Strategy Pattern components*
- *Blue: Factory Pattern components*
- *Green: Single Responsibility Principle components*
- *Red: Interface components*

## Design Patterns Used

### 1. Strategy Pattern (Pink)
- Used in parking spot selection strategy
- Implemented through `ParkingSpotSelector` interface with concrete implementations:
  - `NearestSelector`: Selects the nearest available parking spot
  - `RandomSpotSelector`: Selects a random available parking spot
- Also used in vehicle management through different vehicle type managers
- Allows dynamic switching between different strategies

### 2. Factory Pattern (Blue)
- `VehicleTypeManagerFactory`: Creates appropriate vehicle parking managers based on vehicle type
- `SpotSelectorFactory`: Creates appropriate spot selectors based on selection strategy
- Encapsulates object creation logic and provides a clean interface for client code

### 3. Interface Segregation (Red)
- `VehicleParkingManager` interface defines specific methods for parking management
- `PaymentProcessor` interface for payment processing
- Separate interfaces for different responsibilities

### 4. Single Responsibility Principle (Green)
- Each class has a single responsibility:
  - `TicketGenerator`: Handles ticket generation
  - `ParkingFeeProcessor`: Handles fee calculation and processing
  - `ParkingSpotVacator`: Handles spot vacating logic

## Key Components

### Data Models
- `Vehicle`: Represents a vehicle with type and details
- `ParkingSpot`: Represents a parking spot with location and availability
- `Ticket`: Represents a parking ticket with vehicle and spot information

### Managers
- `VehicleParkingManager`: Interface for vehicle-specific parking management
- Concrete implementations for different vehicle types:
  - `TwoWheelerManager`
  - `FourWheelerManager`
  - `HeavyWheelerManager`

### APIs
- `FindParkingSpotAPI`: Handles parking spot finding
- `GetTicketAPI`: Handles ticket generation
- `PayParkingFeesAPI`: Handles payment processing
- `VacateParkingSpotAPI`: Handles spot vacating

### Payment Processing
- Supports multiple payment modes (Cash/Card)
- Flexible payment processing through `PaymentProcessor` interface

## Features
1. Support for multiple vehicle types
2. Different parking spot selection strategies
3. Flexible payment processing
4. Ticket-based parking management
5. Floor-based parking organization
6. Entry/exit point management

## Usage
The system provides APIs for:
1. Finding available parking spots
2. Generating parking tickets
3. Processing parking fees
4. Vacating parking spots

Each API is designed to be extensible and maintainable, following SOLID principles and design patterns. 