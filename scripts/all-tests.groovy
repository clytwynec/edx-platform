guard {
  parallel(
    job1_1: { build('test-job-one', sha1: params["sha1"], test_spec: "lms/djangoapps/courseware/tests/test_about.py") },
    job1_2: { build('test-job-one', sha1: params["sha1"], test_spec: "cms/djangoapps/contentstore/management/commands/tests/test_import.py") }
  )
} rescue {}
