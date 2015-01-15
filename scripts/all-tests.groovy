guard {
  /*
  parallel(
    lms_unittests: { build('test-job-one', sha1: params["sha1"], test_spec: "lms/djangoapps/courseware/tests/test_about.py") },
    cms_unittests: { build('test-job-one', sha1: params["sha1"], test_spec: "cms/djangoapps/contentstore/management/commands/tests/test_import.py") }
  )
  */

  lms_unittests = build('test-job-one', sha1: params["sha1"], test_spec: "lms/djangoapps/courseware/tests/test_about.py")
  cms_unittests = build('test-job-one', sha1: params["sha1"], test_spec: "cms/djangoapps/contentstore/management/commands/tests/test_import.py")

} rescue {
  def toolbox = extension."build-flow-toolbox"
  toolbox.slurpArtifacts(lms_unittests)
  toolbox.slurpArtifacts(cms_unittests)
}
