# README #
### What is this repository for? ###

* This repository contains elevator simulation algorithms and an application for running the simulations from the command line. It is a maven reactor project; the reactor is named elevator-reactor. It consists of two parts: 
** elevator-sim-core, which is a business logic module containing the algorithms and interfaces.
** elevator-sim, which is the application module which contains command line parsing and functionality from loading input from files.
* Release 1.0

### Set Up ###

* To build the modules, you will need maven and a Java 8 JRE.
* To build the modules, execute 'mvn compile' from the reactor directory.
* To run the tests, run 'mvn test' from the reactor directory or individually for each module.

### Arguments and Output ###

Anybody familiar with the problem specification can skip right to the Solution Details subsection.

#### Scenario Filename ####
The first argument to the simulation should be the filename (with any necessary paths) of a scenario to run. Scenarios are expected to be in the following pattern:
8:3-6,4-6
Where 8 is the initial floor of the elevator, 3-6 is a rider on floor 3 trying to get to floor 6, and 4-6 is a rider on floor 4 trying to get to floor 6.

#### Mode #####

The 'mode' argument can be one of two values: "a" or "b" (case insensitive).
* Mode A: simple strategy that picks up riders in a linear fashion, e.g. 8 to 3, 3 to 6, 6 to 4, then 4 to 6.
* Mode B: slight optimization that will pick up riders going in the same direction, e.g. 8 to 3, 3 to 4, then 4 to 6.

#### Output ####
The output from a scenario <code>'8:7-6'</code> will be <code>8 7 6 (2)</code>, indicating the elevator traveled from floor 8 to 7, 7 to 6, and moved a total of 2 floors.

### Running Simulations ###

To add a custom scenario to the test suites, add the scenario and mode A/mode B solutions to elevator-sim/src/main/resources (the directory structure should be obvious from there). Then add the name of the scenario to ElevatorSimTest.

To run an arbitrary scenario through the simulation from the CLI, invoke "elevator-sim <path to scenario> <mode argument>". The output will be printed to the console. You can call elevator-sim without any arguments, or with the --help argument, to get more information on the usage of the application.

### Solution Details ###

#### Module Layout ####

I chose to break the application into two modules because one could imagine the core module being used in a more complex application, say, ApartmentBuilding, that has more moving parts. For that reason, I used interfaces to represent the Elevator and MoveStrategy concepts. There may be another Elevator implementation, for example, that does something other than write to the command-line, e.g. physically picks up riders from ApartmentBuilding Floors with a fixed capacity. As illustrated, there are already multiple MoveStrategy implementations; undoubtedly there are better optimizations that could be added.

I used guice to inject the proper dependencies for the needs of the elevator-sim application. Also, adding new strategies does not involve changing the existing business logic module's classes (open to extension, closed to modification). Finally, dependency injection lets me use Mockito to write tests using mocks.

#### Solution Details ####

The move from the initial elevator floor to the first rider's pickup floor introduces some complexity to the input into the elevator-sim-core module. Ideally the input is uniform, but this introduces a special case. For that reason, when parsing a scenario, I fabricate a move from the initial floor to the first pickup floor. I do away totally with the concept of the "originating floor" at the business logic level and treat that initial move request the same as all others. The end result is much cleaner MoveCommand algorithm implementations.

For the Mode B implementation, I rely quite heavily on Guava. The original implementation I was hacking out did not do so, and may have been more efficient for that reason, but most likely by only a marginal amount. I did some reading to comfort me that my Guavua-centric approach was reasonably performant: avoiding object creation in Java is counter to the paradigm of the language, and object creation can be assured to be relatively fast. That said, it is still good to avoid _unnecessary_ object creation, and I looked out for that. Do I think I could produce a more efficient algorithm? Probably, yes. If I were to endeavor to do that, though, I would want to use tooling (such as JMeter) to measure performance so I have evidence to go off of. My first goal is to write something that is readable, prosaic, correct, and not grossly inefficient, and then I can address optimization from there. If blazing high-performance becomes an acceptance criteria for applications I'm working on, I would most certainly begin reading/learning about how to produce applications with this trait, beyond the obvious Java knowledge.

Final note: I use Integer objects (rather than primitives) throughout the application quite frequently because I want to avoid tons of auto-boxing/auto-unboxing. Besides that, many of the generic types I'm using require it. From the very start of the application, when parsing an input file, I use Integer.valueOf(String), so it seemed prudent to maintain the same Integer objects throughout the application lifetime as much as possible.
