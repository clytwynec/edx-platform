guard {
  build('test-job-one', sha1: params["sha1"], SHARD: "shard1")
  build('test-job-one', sha1: params["sha1"], SHARD: "shard2")
} rescue {}
