#!/usr/bin/env bats

@test "test" {
  result = $(curl -X (./build/install/evechicle_67/bin/evechicle_67 healthcheck))
  echo $result
}



