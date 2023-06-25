# Problem Statement
Flipkart is starting a new vehicle rental service called FlipKar. In this service, we will rent out different
kinds of vehicles such as cars and bikes.

# Features
1. Rental service has multiple branches throughout the city.
2. Each branch has limited number of different kinds of vehicles.
3. Each vehicle can be booked with the predefined price per unit time slot. 
For simplicity, the current pricing model does not support dynamic pricing.
4. Each vehicle can be booked in multiples of 1-hour slot.
5. Booking can be made for the future date and time.

# Requirements
1. Onboard a new vehicle with the available vehicles.
2. Onboard new vehicle of existing type to a particular branch.
3. Rent vehicle for a time slot and number of seats (lowest price as the default choice of selection of vehicle, this should be extendable to any other strategy).
   a. Implementation should be pluggable for one or more strategy.
   b. While booking if more than 1 vehicle is eligible, then selection should fallback on the next strategy.
   c. For example: for a given branch if both 'sedan' and 'hatchback' are available and have same price priority should be given to sedan (best vehicle strategy).

# Bonus Question
1. Customer should be able to cancel a booking.
2. Customer should be suggested nearest branch as per his/ her requirements (time slot and vehicle type).
    