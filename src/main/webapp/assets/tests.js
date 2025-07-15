'use strict';

define("ember-js-project/tests/helpers/index", ["exports", "ember-qunit"], function (_exports, _emberQunit) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.setupApplicationTest = setupApplicationTest;
  _exports.setupRenderingTest = setupRenderingTest;
  _exports.setupTest = setupTest;
  0; //eaimeta@70e063a35619d71f0,"ember-qunit"eaimeta@70e063a35619d71f
  // This file exists to provide wrappers around ember-qunit's
  // test setup functions. This way, you can easily extend the setup that is
  // needed per test type.

  function setupApplicationTest(hooks, options) {
    (0, _emberQunit.setupApplicationTest)(hooks, options);

    // Additional setup for application tests can be done here.
    //
    // For example, if you need an authenticated session for each
    // application test, you could do:
    //
    // hooks.beforeEach(async function () {
    //   await authenticateSession(); // ember-simple-auth
    // });
    //
    // This is also a good place to call test setup functions coming
    // from other addons:
    //
    // setupIntl(hooks, 'en-us'); // ember-intl
    // setupMirage(hooks); // ember-cli-mirage
  }
  function setupRenderingTest(hooks, options) {
    (0, _emberQunit.setupRenderingTest)(hooks, options);

    // Additional setup for rendering tests can be done here.
  }
  function setupTest(hooks, options) {
    (0, _emberQunit.setupTest)(hooks, options);

    // Additional setup for unit tests can be done here.
  }
});
define("ember-js-project/tests/integration/components/movie-card-test", ["qunit", "ember-js-project/tests/helpers", "@ember/test-helpers", "@ember/template-factory"], function (_qunit, _helpers, _testHelpers, _templateFactory) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers",0,"@ember/test-helpers",0,"@ember/template-factory"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Integration | Component | movie-card', function (hooks) {
    (0, _helpers.setupRenderingTest)(hooks);
    (0, _qunit.test)('it renders', async function (assert) {
      // Set any properties with this.set('myProperty', 'value');
      // Handle any actions with this.set('myAction', function(val) { ... });

      await (0, _testHelpers.render)((0, _templateFactory.createTemplateFactory)(
      /*
        <MovieCard />
      */
      {
        "id": "b8Q3mpgJ",
        "block": "[[[8,[39,0],null,null,null]],[],[\"movie-card\"]]",
        "moduleName": "D:\\zsgs\\DSA\\EmberJSProject\\EmberJsProject\\ember-js-project\\tests\\integration\\components\\movie-card-test.js",
        "isStrictMode": false
      }));
      assert.dom().hasText('');

      // Template block usage:
      await (0, _testHelpers.render)((0, _templateFactory.createTemplateFactory)(
      /*
        
            <MovieCard>
              template block text
            </MovieCard>
          
      */
      {
        "id": "LLZO8cKS",
        "block": "[[[1,\"\\n      \"],[8,[39,0],null,null,[[\"default\"],[[[[1,\"\\n        template block text\\n      \"]],[]]]]],[1,\"\\n    \"]],[],[\"movie-card\"]]",
        "moduleName": "D:\\zsgs\\DSA\\EmberJSProject\\EmberJsProject\\ember-js-project\\tests\\integration\\components\\movie-card-test.js",
        "isStrictMode": false
      }));
      assert.dom().hasText('template block text');
    });
  });
});
define("ember-js-project/tests/integration/components/show-card-test", ["qunit", "ember-js-project/tests/helpers", "@ember/test-helpers", "@ember/template-factory"], function (_qunit, _helpers, _testHelpers, _templateFactory) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers",0,"@ember/test-helpers",0,"@ember/template-factory"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Integration | Component | show-card', function (hooks) {
    (0, _helpers.setupRenderingTest)(hooks);
    (0, _qunit.test)('it renders', async function (assert) {
      // Set any properties with this.set('myProperty', 'value');
      // Handle any actions with this.set('myAction', function(val) { ... });

      await (0, _testHelpers.render)((0, _templateFactory.createTemplateFactory)(
      /*
        <ShowCard />
      */
      {
        "id": "c5NFwouY",
        "block": "[[[8,[39,0],null,null,null]],[],[\"show-card\"]]",
        "moduleName": "D:\\zsgs\\DSA\\EmberJSProject\\EmberJsProject\\ember-js-project\\tests\\integration\\components\\show-card-test.js",
        "isStrictMode": false
      }));
      assert.dom().hasText('');

      // Template block usage:
      await (0, _testHelpers.render)((0, _templateFactory.createTemplateFactory)(
      /*
        
            <ShowCard>
              template block text
            </ShowCard>
          
      */
      {
        "id": "8XvBO7Bj",
        "block": "[[[1,\"\\n      \"],[8,[39,0],null,null,[[\"default\"],[[[[1,\"\\n        template block text\\n      \"]],[]]]]],[1,\"\\n    \"]],[],[\"show-card\"]]",
        "moduleName": "D:\\zsgs\\DSA\\EmberJSProject\\EmberJsProject\\ember-js-project\\tests\\integration\\components\\show-card-test.js",
        "isStrictMode": false
      }));
      assert.dom().hasText('template block text');
    });
  });
});
define("ember-js-project/tests/integration/components/show-seats-dialog-test", ["qunit", "ember-js-project/tests/helpers", "@ember/test-helpers", "@ember/template-factory"], function (_qunit, _helpers, _testHelpers, _templateFactory) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers",0,"@ember/test-helpers",0,"@ember/template-factory"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Integration | Component | show-seats-dialog', function (hooks) {
    (0, _helpers.setupRenderingTest)(hooks);
    (0, _qunit.test)('it renders', async function (assert) {
      // Set any properties with this.set('myProperty', 'value');
      // Handle any actions with this.set('myAction', function(val) { ... });

      await (0, _testHelpers.render)((0, _templateFactory.createTemplateFactory)(
      /*
        <ShowSeatsDialog />
      */
      {
        "id": "3q9D+QZZ",
        "block": "[[[8,[39,0],null,null,null]],[],[\"show-seats-dialog\"]]",
        "moduleName": "D:\\zsgs\\DSA\\EmberJSProject\\EmberJsProject\\ember-js-project\\tests\\integration\\components\\show-seats-dialog-test.js",
        "isStrictMode": false
      }));
      assert.dom().hasText('');

      // Template block usage:
      await (0, _testHelpers.render)((0, _templateFactory.createTemplateFactory)(
      /*
        
            <ShowSeatsDialog>
              template block text
            </ShowSeatsDialog>
          
      */
      {
        "id": "tVQu5X1w",
        "block": "[[[1,\"\\n      \"],[8,[39,0],null,null,[[\"default\"],[[[[1,\"\\n        template block text\\n      \"]],[]]]]],[1,\"\\n    \"]],[],[\"show-seats-dialog\"]]",
        "moduleName": "D:\\zsgs\\DSA\\EmberJSProject\\EmberJsProject\\ember-js-project\\tests\\integration\\components\\show-seats-dialog-test.js",
        "isStrictMode": false
      }));
      assert.dom().hasText('template block text');
    });
  });
});
define("ember-js-project/tests/integration/components/showtime-dialog-test", ["qunit", "ember-js-project/tests/helpers", "@ember/test-helpers", "@ember/template-factory"], function (_qunit, _helpers, _testHelpers, _templateFactory) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers",0,"@ember/test-helpers",0,"@ember/template-factory"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Integration | Component | showtime-dialog', function (hooks) {
    (0, _helpers.setupRenderingTest)(hooks);
    (0, _qunit.test)('it renders', async function (assert) {
      // Set any properties with this.set('myProperty', 'value');
      // Handle any actions with this.set('myAction', function(val) { ... });

      await (0, _testHelpers.render)((0, _templateFactory.createTemplateFactory)(
      /*
        <ShowtimeDialog />
      */
      {
        "id": "ATbmVt7m",
        "block": "[[[8,[39,0],null,null,null]],[],[\"showtime-dialog\"]]",
        "moduleName": "D:\\zsgs\\DSA\\EmberJSProject\\EmberJsProject\\ember-js-project\\tests\\integration\\components\\showtime-dialog-test.js",
        "isStrictMode": false
      }));
      assert.dom().hasText('');

      // Template block usage:
      await (0, _testHelpers.render)((0, _templateFactory.createTemplateFactory)(
      /*
        
            <ShowtimeDialog>
              template block text
            </ShowtimeDialog>
          
      */
      {
        "id": "TuQ/2bah",
        "block": "[[[1,\"\\n      \"],[8,[39,0],null,null,[[\"default\"],[[[[1,\"\\n        template block text\\n      \"]],[]]]]],[1,\"\\n    \"]],[],[\"showtime-dialog\"]]",
        "moduleName": "D:\\zsgs\\DSA\\EmberJSProject\\EmberJsProject\\ember-js-project\\tests\\integration\\components\\showtime-dialog-test.js",
        "isStrictMode": false
      }));
      assert.dom().hasText('template block text');
    });
  });
});
define("ember-js-project/tests/integration/components/theatre-card-test", ["qunit", "ember-js-project/tests/helpers", "@ember/test-helpers", "@ember/template-factory"], function (_qunit, _helpers, _testHelpers, _templateFactory) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers",0,"@ember/test-helpers",0,"@ember/template-factory"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Integration | Component | theatre-card', function (hooks) {
    (0, _helpers.setupRenderingTest)(hooks);
    (0, _qunit.test)('it renders', async function (assert) {
      // Set any properties with this.set('myProperty', 'value');
      // Handle any actions with this.set('myAction', function(val) { ... });

      await (0, _testHelpers.render)((0, _templateFactory.createTemplateFactory)(
      /*
        <TheatreCard />
      */
      {
        "id": "JQj06vQh",
        "block": "[[[8,[39,0],null,null,null]],[],[\"theatre-card\"]]",
        "moduleName": "D:\\zsgs\\DSA\\EmberJSProject\\EmberJsProject\\ember-js-project\\tests\\integration\\components\\theatre-card-test.js",
        "isStrictMode": false
      }));
      assert.dom().hasText('');

      // Template block usage:
      await (0, _testHelpers.render)((0, _templateFactory.createTemplateFactory)(
      /*
        
            <TheatreCard>
              template block text
            </TheatreCard>
          
      */
      {
        "id": "wE73etFk",
        "block": "[[[1,\"\\n      \"],[8,[39,0],null,null,[[\"default\"],[[[[1,\"\\n        template block text\\n      \"]],[]]]]],[1,\"\\n    \"]],[],[\"theatre-card\"]]",
        "moduleName": "D:\\zsgs\\DSA\\EmberJSProject\\EmberJsProject\\ember-js-project\\tests\\integration\\components\\theatre-card-test.js",
        "isStrictMode": false
      }));
      assert.dom().hasText('template block text');
    });
  });
});
define("ember-js-project/tests/integration/helpers/includes-test", ["qunit", "ember-js-project/tests/helpers", "@ember/test-helpers", "@ember/template-factory"], function (_qunit, _helpers, _testHelpers, _templateFactory) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers",0,"@ember/test-helpers",0,"@ember/template-factory"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Integration | Helper | includes', function (hooks) {
    (0, _helpers.setupRenderingTest)(hooks);

    // TODO: Replace this with your real tests.
    (0, _qunit.test)('it renders', async function (assert) {
      this.set('inputValue', '1234');
      await (0, _testHelpers.render)((0, _templateFactory.createTemplateFactory)(
      /*
        {{includes this.inputValue}}
      */
      {
        "id": "q/JvSTvW",
        "block": "[[[1,[28,[35,0],[[30,0,[\"inputValue\"]]],null]]],[],[\"includes\"]]",
        "moduleName": "D:\\zsgs\\DSA\\EmberJSProject\\EmberJsProject\\ember-js-project\\tests\\integration\\helpers\\includes-test.js",
        "isStrictMode": false
      }));
      assert.dom().hasText('1234');
    });
  });
});
define("ember-js-project/tests/test-helper", ["ember-js-project/app", "ember-js-project/config/environment", "qunit", "@ember/test-helpers", "qunit-dom", "ember-qunit/test-loader", "ember-qunit"], function (_app, _environment, QUnit, _testHelpers, _qunitDom, _testLoader, _emberQunit) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"ember-js-project/app",0,"ember-js-project/config/environment",0,"qunit",0,"@ember/test-helpers",0,"qunit-dom",0,"ember-qunit/test-loader",0,"ember-qunit"eaimeta@70e063a35619d71f
  (0, _testHelpers.setApplication)(_app.default.create(_environment.default.APP));
  (0, _qunitDom.setup)(QUnit.assert);
  (0, _emberQunit.setupEmberOnerrorValidation)();
  (0, _testLoader.loadTests)();
  (0, _emberQunit.start)();
});
define("ember-js-project/tests/unit/routes/login-test", ["qunit", "ember-js-project/tests/helpers"], function (_qunit, _helpers) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Unit | Route | login', function (hooks) {
    (0, _helpers.setupTest)(hooks);
    (0, _qunit.test)('it exists', function (assert) {
      let route = this.owner.lookup('route:login');
      assert.ok(route);
    });
  });
});
define("ember-js-project/tests/unit/routes/movie-card-test", ["qunit", "ember-js-project/tests/helpers"], function (_qunit, _helpers) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Unit | Route | movie-card', function (hooks) {
    (0, _helpers.setupTest)(hooks);
    (0, _qunit.test)('it exists', function (assert) {
      let route = this.owner.lookup('route:movie-card');
      assert.ok(route);
    });
  });
});
define("ember-js-project/tests/unit/routes/movie-test", ["qunit", "ember-js-project/tests/helpers"], function (_qunit, _helpers) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Unit | Route | movie', function (hooks) {
    (0, _helpers.setupTest)(hooks);
    (0, _qunit.test)('it exists', function (assert) {
      let route = this.owner.lookup('route:movie');
      assert.ok(route);
    });
  });
});
define("ember-js-project/tests/unit/routes/movies-test", ["qunit", "ember-js-project/tests/helpers"], function (_qunit, _helpers) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Unit | Route | movies', function (hooks) {
    (0, _helpers.setupTest)(hooks);
    (0, _qunit.test)('it exists', function (assert) {
      let route = this.owner.lookup('route:movies');
      assert.ok(route);
    });
  });
});
define("ember-js-project/tests/unit/routes/signup-test", ["qunit", "ember-js-project/tests/helpers"], function (_qunit, _helpers) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Unit | Route | signup', function (hooks) {
    (0, _helpers.setupTest)(hooks);
    (0, _qunit.test)('it exists', function (assert) {
      let route = this.owner.lookup('route:signup');
      assert.ok(route);
    });
  });
});
define("ember-js-project/tests/unit/services/movie-state-test", ["qunit", "ember-js-project/tests/helpers"], function (_qunit, _helpers) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"qunit",0,"ember-js-project/tests/helpers"eaimeta@70e063a35619d71f
  (0, _qunit.module)('Unit | Service | movie-state', function (hooks) {
    (0, _helpers.setupTest)(hooks);

    // TODO: Replace this with your real tests.
    (0, _qunit.test)('it exists', function (assert) {
      let service = this.owner.lookup('service:movie-state');
      assert.ok(service);
    });
  });
});
define('ember-js-project/config/environment', [], function() {
  var prefix = 'ember-js-project';
try {
  var metaName = prefix + '/config/environment';
  var rawConfig = document.querySelector('meta[name="' + metaName + '"]').getAttribute('content');
  var config = JSON.parse(decodeURIComponent(rawConfig));

  var exports = { 'default': config };

  Object.defineProperty(exports, '__esModule', { value: true });

  return exports;
}
catch(err) {
  throw new Error('Could not read config from meta tag with name "' + metaName + '".');
}

});

require('ember-js-project/tests/test-helper');
EmberENV.TESTS_FILE_LOADED = true;
//# sourceMappingURL=tests.map
