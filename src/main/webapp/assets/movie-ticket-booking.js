'use strict';



;define("movie-ticket-booking/app", ["exports", "@ember/application", "ember-resolver", "ember-load-initializers", "movie-ticket-booking/config/environment", "@embroider/macros/es-compat2", "movie-ticket-booking/deprecation-workflow"], function (_exports, _application, _emberResolver, _emberLoadInitializers, _environment, _esCompat, _importSync20) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember/application",0,"ember-resolver",0,"ember-load-initializers",0,"movie-ticket-booking/config/environment",0,"@embroider/macros",0,"./deprecation-workflow"eaimeta@70e063a35619d71f
  function _defineProperty(e, r, t) { return (r = _toPropertyKey(r)) in e ? Object.defineProperty(e, r, { value: t, enumerable: !0, configurable: !0, writable: !0 }) : e[r] = t, e; }
  function _toPropertyKey(t) { var i = _toPrimitive(t, "string"); return "symbol" == typeof i ? i : i + ""; }
  function _toPrimitive(t, r) { if ("object" != typeof t || !t) return t; var e = t[Symbol.toPrimitive]; if (void 0 !== e) { var i = e.call(t, r || "default"); if ("object" != typeof i) return i; throw new TypeError("@@toPrimitive must return a primitive value."); } return ("string" === r ? String : Number)(t); }
  {
    (0, _esCompat.default)(_importSync20);
  }
  class App extends _application.default {
    constructor(...args) {
      super(...args);
      _defineProperty(this, "modulePrefix", _environment.default.modulePrefix);
      _defineProperty(this, "podModulePrefix", _environment.default.podModulePrefix);
      _defineProperty(this, "Resolver", _emberResolver.default);
    }
  }
  _exports.default = App;
  (0, _emberLoadInitializers.default)(App, _environment.default.modulePrefix);
});
;define("movie-ticket-booking/components/auth-button", ["exports", "@ember/component", "@ember/component/template-only", "@ember/template-factory"], function (_exports, _component, _templateOnly, _templateFactory) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember/component/template-only",0,"@ember/template-factory",0,"@ember/component"eaimeta@70e063a35619d71f
  const __COLOCATED_TEMPLATE__ = (0, _templateFactory.createTemplateFactory)(
  /*
    <button type={{@type}} class="button">
    {{yield}}
  </button>
  
  */
  {
    "id": "LuLPxY19",
    "block": "[[[10,\"button\"],[14,0,\"button\"],[15,4,[30,1]],[12],[1,\"\\n  \"],[18,2,null],[1,\"\\n\"],[13],[1,\"\\n\"]],[\"@type\",\"&default\"],[\"button\",\"yield\"]]",
    "moduleName": "movie-ticket-booking/components/auth-button.hbs",
    "isStrictMode": false
  });
  var _default = _exports.default = (0, _component.setComponentTemplate)(__COLOCATED_TEMPLATE__, (0, _templateOnly.default)(undefined, "auth-button"));
});
;define("movie-ticket-booking/components/welcome-page", ["exports", "ember-welcome-page/components/welcome-page"], function (_exports, _welcomePage) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  Object.defineProperty(_exports, "default", {
    enumerable: true,
    get: function () {
      return _welcomePage.default;
    }
  });
  0; //eaimeta@70e063a35619d71f0,"ember-welcome-page/components/welcome-page"eaimeta@70e063a35619d71f
});
;define("movie-ticket-booking/controllers/login", ["exports", "@ember/controller", "@ember/object", "@glimmer/tracking"], function (_exports, _controller, _object, _tracking) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  var _class, _descriptor, _descriptor2;
  0; //eaimeta@70e063a35619d71f0,"@ember/controller",0,"@ember/object",0,"@glimmer/tracking"eaimeta@70e063a35619d71f
  function _initializerDefineProperty(e, i, r, l) { r && Object.defineProperty(e, i, { enumerable: r.enumerable, configurable: r.configurable, writable: r.writable, value: r.initializer ? r.initializer.call(l) : void 0 }); }
  function _defineProperty(e, r, t) { return (r = _toPropertyKey(r)) in e ? Object.defineProperty(e, r, { value: t, enumerable: !0, configurable: !0, writable: !0 }) : e[r] = t, e; }
  function _toPropertyKey(t) { var i = _toPrimitive(t, "string"); return "symbol" == typeof i ? i : i + ""; }
  function _toPrimitive(t, r) { if ("object" != typeof t || !t) return t; var e = t[Symbol.toPrimitive]; if (void 0 !== e) { var i = e.call(t, r || "default"); if ("object" != typeof i) return i; throw new TypeError("@@toPrimitive must return a primitive value."); } return ("string" === r ? String : Number)(t); }
  function _applyDecoratedDescriptor(i, e, r, n, l) { var a = {}; return Object.keys(n).forEach(function (i) { a[i] = n[i]; }), a.enumerable = !!a.enumerable, a.configurable = !!a.configurable, ("value" in a || a.initializer) && (a.writable = !0), a = r.slice().reverse().reduce(function (r, n) { return n(i, e, r) || r; }, a), l && void 0 !== a.initializer && (a.value = a.initializer ? a.initializer.call(l) : void 0, a.initializer = void 0), void 0 === a.initializer ? (Object.defineProperty(i, e, a), null) : a; }
  function _initializerWarningHelper(r, e) { throw Error("Decorating class property failed. Please ensure that transform-class-properties is enabled and runs after the decorators transform."); }
  let LoginController = _exports.default = (_class = class LoginController extends _controller.default {
    constructor(...args) {
      super(...args);
      _initializerDefineProperty(this, "email", _descriptor, this);
      _initializerDefineProperty(this, "password", _descriptor2, this);
    }
    updateEmailLogin(event) {
      this.email = event.target.value;
    }
    updatePasswordLogin(event) {
      this.password = event.target.value;
    }
    handleLogin(event) {
      event.preventDefault();
    }
  }, _descriptor = _applyDecoratedDescriptor(_class.prototype, "email", [_tracking.tracked], {
    configurable: true,
    enumerable: true,
    writable: true,
    initializer: function () {
      return '';
    }
  }), _descriptor2 = _applyDecoratedDescriptor(_class.prototype, "password", [_tracking.tracked], {
    configurable: true,
    enumerable: true,
    writable: true,
    initializer: function () {
      return '';
    }
  }), _applyDecoratedDescriptor(_class.prototype, "updateEmailLogin", [_object.action], Object.getOwnPropertyDescriptor(_class.prototype, "updateEmailLogin"), _class.prototype), _applyDecoratedDescriptor(_class.prototype, "updatePasswordLogin", [_object.action], Object.getOwnPropertyDescriptor(_class.prototype, "updatePasswordLogin"), _class.prototype), _applyDecoratedDescriptor(_class.prototype, "handleLogin", [_object.action], Object.getOwnPropertyDescriptor(_class.prototype, "handleLogin"), _class.prototype), _class);
});
;define("movie-ticket-booking/controllers/signup", ["exports", "@ember/controller", "@ember/object", "@glimmer/tracking"], function (_exports, _controller, _object, _tracking) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  var _class, _descriptor, _descriptor2, _descriptor3, _descriptor4;
  0; //eaimeta@70e063a35619d71f0,"@ember/controller",0,"@ember/object",0,"@glimmer/tracking"eaimeta@70e063a35619d71f
  function _initializerDefineProperty(e, i, r, l) { r && Object.defineProperty(e, i, { enumerable: r.enumerable, configurable: r.configurable, writable: r.writable, value: r.initializer ? r.initializer.call(l) : void 0 }); }
  function _defineProperty(e, r, t) { return (r = _toPropertyKey(r)) in e ? Object.defineProperty(e, r, { value: t, enumerable: !0, configurable: !0, writable: !0 }) : e[r] = t, e; }
  function _toPropertyKey(t) { var i = _toPrimitive(t, "string"); return "symbol" == typeof i ? i : i + ""; }
  function _toPrimitive(t, r) { if ("object" != typeof t || !t) return t; var e = t[Symbol.toPrimitive]; if (void 0 !== e) { var i = e.call(t, r || "default"); if ("object" != typeof i) return i; throw new TypeError("@@toPrimitive must return a primitive value."); } return ("string" === r ? String : Number)(t); }
  function _applyDecoratedDescriptor(i, e, r, n, l) { var a = {}; return Object.keys(n).forEach(function (i) { a[i] = n[i]; }), a.enumerable = !!a.enumerable, a.configurable = !!a.configurable, ("value" in a || a.initializer) && (a.writable = !0), a = r.slice().reverse().reduce(function (r, n) { return n(i, e, r) || r; }, a), l && void 0 !== a.initializer && (a.value = a.initializer ? a.initializer.call(l) : void 0, a.initializer = void 0), void 0 === a.initializer ? (Object.defineProperty(i, e, a), null) : a; }
  function _initializerWarningHelper(r, e) { throw Error("Decorating class property failed. Please ensure that transform-class-properties is enabled and runs after the decorators transform."); }
  let SignUpController = _exports.default = (_class = class SignUpController extends _controller.default {
    constructor(...args) {
      super(...args);
      _initializerDefineProperty(this, "email", _descriptor, this);
      _initializerDefineProperty(this, "password", _descriptor2, this);
      _initializerDefineProperty(this, "userName", _descriptor3, this);
      _initializerDefineProperty(this, "responseMsg", _descriptor4, this);
    }
    updateEmailSignUp(event) {
      this.email = event.target.value;
    }
    updatePasswordSignUp(event) {
      this.password = event.target.value;
    }
    updateUserNameSignUp(event) {
      this.userName = event.target.value;
    }
    async handleSignUp(event) {
      event.preventDefault();
      try {
        const response = await fetch('http://localhost:8080/api/v1/signup', {
          method: 'POST',
          body: JSON.stringify({
            email: this.email,
            password: this.password
          })
        });
        const result = await response.json();
        if (result.status === 'success') {
          this.signupMessage = result.message;
          this.userId = result.userId;
          alert(`Signup successful!\nUser ID: ${this.userId}`);
        } else {
          this.signupMessage = result.message;
          alert(`Signup failed: ${result.message}`);
        }
      } catch (error) {
        console.error('Signup failed:', error);
        this.signupMessage = 'Something went wrong.';
      }
    }
  }, _descriptor = _applyDecoratedDescriptor(_class.prototype, "email", [_tracking.tracked], {
    configurable: true,
    enumerable: true,
    writable: true,
    initializer: function () {
      return '';
    }
  }), _descriptor2 = _applyDecoratedDescriptor(_class.prototype, "password", [_tracking.tracked], {
    configurable: true,
    enumerable: true,
    writable: true,
    initializer: function () {
      return '';
    }
  }), _descriptor3 = _applyDecoratedDescriptor(_class.prototype, "userName", [_tracking.tracked], {
    configurable: true,
    enumerable: true,
    writable: true,
    initializer: function () {
      return '';
    }
  }), _descriptor4 = _applyDecoratedDescriptor(_class.prototype, "responseMsg", [_tracking.tracked], {
    configurable: true,
    enumerable: true,
    writable: true,
    initializer: function () {
      return '';
    }
  }), _applyDecoratedDescriptor(_class.prototype, "updateEmailSignUp", [_object.action], Object.getOwnPropertyDescriptor(_class.prototype, "updateEmailSignUp"), _class.prototype), _applyDecoratedDescriptor(_class.prototype, "updatePasswordSignUp", [_object.action], Object.getOwnPropertyDescriptor(_class.prototype, "updatePasswordSignUp"), _class.prototype), _applyDecoratedDescriptor(_class.prototype, "updateUserNameSignUp", [_object.action], Object.getOwnPropertyDescriptor(_class.prototype, "updateUserNameSignUp"), _class.prototype), _applyDecoratedDescriptor(_class.prototype, "handleSignUp", [_object.action], Object.getOwnPropertyDescriptor(_class.prototype, "handleSignUp"), _class.prototype), _class);
});
;define("movie-ticket-booking/data-adapter", ["exports", "@ember-data/debug/data-adapter"], function (_exports, _dataAdapter) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  Object.defineProperty(_exports, "default", {
    enumerable: true,
    get: function () {
      return _dataAdapter.default;
    }
  });
  0; //eaimeta@70e063a35619d71f0,"@ember-data/debug/data-adapter"eaimeta@70e063a35619d71f
});
;define("movie-ticket-booking/deprecation-workflow", ["ember-cli-deprecation-workflow"], function (_emberCliDeprecationWorkflow) {
  "use strict";

  0; //eaimeta@70e063a35619d71f0,"ember-cli-deprecation-workflow"eaimeta@70e063a35619d71f
  /**
   * Docs: https://github.com/ember-cli/ember-cli-deprecation-workflow
   */
  (0, _emberCliDeprecationWorkflow.default)({
    /**
      false by default, but if a developer / team wants to be more aggressive about being proactive with
      handling their deprecations, this should be set to "true"
    */
    throwOnUnhandled: false,
    workflow: [
      /* ... handlers ... */
      /* to generate this list, run your app for a while (or run the test suite),
       * and then run in the browser console:
       *
       *    deprecationWorkflow.flushDeprecations()
       *
       * And copy the handlers here
       */
      /* example: */
      /* { handler: 'silence', matchId: 'template-action' }, */
    ]
  });
});
;define("movie-ticket-booking/helpers/app-version", ["exports", "@ember/component/helper", "movie-ticket-booking/config/environment", "ember-cli-app-version/utils/regexp"], function (_exports, _helper, _environment, _regexp) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.appVersion = appVersion;
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember/component/helper",0,"movie-ticket-booking/config/environment",0,"ember-cli-app-version/utils/regexp"eaimeta@70e063a35619d71f
  function appVersion(_, hash = {}) {
    const version = _environment.default.APP.version;
    // e.g. 1.0.0-alpha.1+4jds75hf

    // Allow use of 'hideSha' and 'hideVersion' For backwards compatibility
    let versionOnly = hash.versionOnly || hash.hideSha;
    let shaOnly = hash.shaOnly || hash.hideVersion;
    let match = null;
    if (versionOnly) {
      if (hash.showExtended) {
        match = version.match(_regexp.versionExtendedRegExp); // 1.0.0-alpha.1
      }
      // Fallback to just version
      if (!match) {
        match = version.match(_regexp.versionRegExp); // 1.0.0
      }
    }
    if (shaOnly) {
      match = version.match(_regexp.shaRegExp); // 4jds75hf
    }
    return match ? match[0] : version;
  }
  var _default = _exports.default = (0, _helper.helper)(appVersion);
});
;define("movie-ticket-booking/helpers/page-title", ["exports", "ember-page-title/helpers/page-title"], function (_exports, _pageTitle) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  Object.defineProperty(_exports, "default", {
    enumerable: true,
    get: function () {
      return _pageTitle.default;
    }
  });
  0; //eaimeta@70e063a35619d71f0,"ember-page-title/helpers/page-title"eaimeta@70e063a35619d71f
});
;define("movie-ticket-booking/initializers/app-version", ["exports", "ember-cli-app-version/initializer-factory", "movie-ticket-booking/config/environment"], function (_exports, _initializerFactory, _environment) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"ember-cli-app-version/initializer-factory",0,"movie-ticket-booking/config/environment"eaimeta@70e063a35619d71f
  let name, version;
  if (_environment.default.APP) {
    name = _environment.default.APP.name;
    version = _environment.default.APP.version;
  }
  var _default = _exports.default = {
    name: 'App Version',
    initialize: (0, _initializerFactory.default)(name, version)
  };
});
;define("movie-ticket-booking/initializers/ember-data", ["exports", "@ember-data/request-utils/deprecation-support"], function (_exports, _deprecationSupport) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember-data/request-utils/deprecation-support"eaimeta@70e063a35619d71f
  /*
    This code initializes EmberData in an Ember application.
  */
  var _default = _exports.default = {
    name: 'ember-data',
    initialize(application) {
      application.registerOptionsForType('serializer', {
        singleton: false
      });
      application.registerOptionsForType('adapter', {
        singleton: false
      });
    }
  };
});
;define("movie-ticket-booking/router", ["exports", "@ember/routing/router", "movie-ticket-booking/config/environment"], function (_exports, _router, _environment) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember/routing/router",0,"movie-ticket-booking/config/environment"eaimeta@70e063a35619d71f
  function _defineProperty(e, r, t) { return (r = _toPropertyKey(r)) in e ? Object.defineProperty(e, r, { value: t, enumerable: !0, configurable: !0, writable: !0 }) : e[r] = t, e; }
  function _toPropertyKey(t) { var i = _toPrimitive(t, "string"); return "symbol" == typeof i ? i : i + ""; }
  function _toPrimitive(t, r) { if ("object" != typeof t || !t) return t; var e = t[Symbol.toPrimitive]; if (void 0 !== e) { var i = e.call(t, r || "default"); if ("object" != typeof i) return i; throw new TypeError("@@toPrimitive must return a primitive value."); } return ("string" === r ? String : Number)(t); }
  class Router extends _router.default {
    constructor(...args) {
      super(...args);
      _defineProperty(this, "location", _environment.default.locationType);
      _defineProperty(this, "rootURL", _environment.default.rootURL);
    }
  }
  _exports.default = Router;
  Router.map(function () {
    this.route('signup');
    this.route('login');
    this.route('not-found', {
      path: "/*path"
    });
  });
});
;define("movie-ticket-booking/routes/application", ["exports", "@ember/routing/route"], function (_exports, _route) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember/routing/route"eaimeta@70e063a35619d71f
  class ApplicationRoute extends _route.default {}
  _exports.default = ApplicationRoute;
});
;define("movie-ticket-booking/routes/login", ["exports", "@ember/routing/route"], function (_exports, _route) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember/routing/route"eaimeta@70e063a35619d71f
  class LoginRoute extends _route.default {}
  _exports.default = LoginRoute;
});
;define("movie-ticket-booking/routes/not-found", ["exports", "@ember/routing/route"], function (_exports, _route) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember/routing/route"eaimeta@70e063a35619d71f
  class NotFoundRoute extends _route.default {}
  _exports.default = NotFoundRoute;
});
;define("movie-ticket-booking/services/page-title", ["exports", "ember-page-title/services/page-title"], function (_exports, _pageTitle) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  Object.defineProperty(_exports, "default", {
    enumerable: true,
    get: function () {
      return _pageTitle.default;
    }
  });
  0; //eaimeta@70e063a35619d71f0,"ember-page-title/services/page-title"eaimeta@70e063a35619d71f
});
;define("movie-ticket-booking/services/store", ["exports", "@ember/debug", "ember-data/store"], function (_exports, _debug, _store) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  Object.defineProperty(_exports, "default", {
    enumerable: true,
    get: function () {
      return _store.default;
    }
  });
  0; //eaimeta@70e063a35619d71f0,"@ember/debug",0,"ember-data/store"eaimeta@70e063a35619d71f
  (true && !(false) && (0, _debug.deprecate)("You are relying on ember-data auto-magically installing the store service. Use `export { default } from 'ember-data/store';` in app/services/store.js instead", false, {
    id: 'ember-data:deprecate-legacy-imports',
    for: 'ember-data',
    until: '6.0',
    since: {
      enabled: '5.2',
      available: '4.13'
    }
  }));
});
;define("movie-ticket-booking/templates/application", ["exports", "@ember/template-factory"], function (_exports, _templateFactory) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember/template-factory"eaimeta@70e063a35619d71f
  var _default = _exports.default = (0, _templateFactory.createTemplateFactory)(
  /*
    {{page-title "Movie-Ticket-Booking"}}
  {{outlet}}
  */
  {
    "id": "Mm7crxKx",
    "block": "[[[1,[28,[35,0],[\"Movie-Ticket-Booking\"],null]],[1,\"\\n\"],[46,[28,[37,2],null,null],null,null,null]],[],[\"page-title\",\"component\",\"-outlet\"]]",
    "moduleName": "movie-ticket-booking/templates/application.hbs",
    "isStrictMode": false
  });
});
;define("movie-ticket-booking/templates/login", ["exports", "@ember/template-factory"], function (_exports, _templateFactory) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember/template-factory"eaimeta@70e063a35619d71f
  var _default = _exports.default = (0, _templateFactory.createTemplateFactory)(
  /*
    {{page-title "Login"}}
  <div class="form-container">
    <form {{on "submit" this.handleLogin}}>
      <h2>Login</h2>
      <input type="email" placeholder="Email" name="email"  {{on "input" this.updateEmailLogin}} required />
      <input type="password" placeholder="Password" name="password" {{on "input" this.updatePasswordLogin}} required />
      <button type="submit">Login</button>
      <p>Don't have an account? <LinkTo @route="signup">Sign up here</LinkTo></p>
    </form>
  </div>
  
  */
  {
    "id": "0+VAWEGI",
    "block": "[[[1,[28,[35,0],[\"Login\"],null]],[1,\"\\n\"],[10,0],[14,0,\"form-container\"],[12],[1,\"\\n  \"],[11,\"form\"],[4,[38,3],[\"submit\",[30,0,[\"handleLogin\"]]],null],[12],[1,\"\\n    \"],[10,\"h2\"],[12],[1,\"Login\"],[13],[1,\"\\n    \"],[11,\"input\"],[24,\"placeholder\",\"Email\"],[24,3,\"email\"],[24,\"required\",\"\"],[24,4,\"email\"],[4,[38,3],[\"input\",[30,0,[\"updateEmailLogin\"]]],null],[12],[13],[1,\"\\n    \"],[11,\"input\"],[24,\"placeholder\",\"Password\"],[24,3,\"password\"],[24,\"required\",\"\"],[24,4,\"password\"],[4,[38,3],[\"input\",[30,0,[\"updatePasswordLogin\"]]],null],[12],[13],[1,\"\\n    \"],[10,\"button\"],[14,4,\"submit\"],[12],[1,\"Login\"],[13],[1,\"\\n    \"],[10,2],[12],[1,\"Don't have an account? \"],[8,[39,8],null,[[\"@route\"],[\"signup\"]],[[\"default\"],[[[[1,\"Sign up here\"]],[]]]]],[13],[1,\"\\n  \"],[13],[1,\"\\n\"],[13],[1,\"\\n\"]],[],[\"page-title\",\"div\",\"form\",\"on\",\"h2\",\"input\",\"button\",\"p\",\"link-to\"]]",
    "moduleName": "movie-ticket-booking/templates/login.hbs",
    "isStrictMode": false
  });
});
;define("movie-ticket-booking/templates/not-found", ["exports", "@ember/template-factory"], function (_exports, _templateFactory) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember/template-factory"eaimeta@70e063a35619d71f
  var _default = _exports.default = (0, _templateFactory.createTemplateFactory)(
  /*
    404 found
  */
  {
    "id": "KmxYa3l7",
    "block": "[[[1,\"404 found\"]],[],[]]",
    "moduleName": "movie-ticket-booking/templates/not-found.hbs",
    "isStrictMode": false
  });
});
;define("movie-ticket-booking/templates/signup", ["exports", "@ember/template-factory"], function (_exports, _templateFactory) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  _exports.default = void 0;
  0; //eaimeta@70e063a35619d71f0,"@ember/template-factory"eaimeta@70e063a35619d71f
  var _default = _exports.default = (0, _templateFactory.createTemplateFactory)(
  /*
    <div class="form-container">
      <form {{on "submit" this.handleSignUp}}>
          <h2>Sign Up</h2>
          <input type="text" placeholder="Full Name" name="name"  {{on "input" this.updateUserNameSignUp}} required />
          <input type="email" placeholder="Email" name="email" {{on "input" this.updateEmailSignUp}} required />
          <input type="password" placeholder="Password" name="password" {{on "input" this.updatePasswordSignUp}} required />
          <AuthButton @type="submit">Create Account</AuthButton>
          <p>Already have an account? <LinkTo @route = "login">Login here</LinkTo></p>
          <br>
          {{#if this.responseMsg}}
          <p><strong>this.responseMsg</strong></p>
          {{/if}}
      </form>
  </div>
  */
  {
    "id": "rpq7A0l5",
    "block": "[[[10,0],[14,0,\"form-container\"],[12],[1,\"\\n    \"],[11,\"form\"],[4,[38,2],[\"submit\",[30,0,[\"handleSignUp\"]]],null],[12],[1,\"\\n        \"],[10,\"h2\"],[12],[1,\"Sign Up\"],[13],[1,\"\\n        \"],[11,\"input\"],[24,\"placeholder\",\"Full Name\"],[24,3,\"name\"],[24,\"required\",\"\"],[24,4,\"text\"],[4,[38,2],[\"input\",[30,0,[\"updateUserNameSignUp\"]]],null],[12],[13],[1,\"\\n        \"],[11,\"input\"],[24,\"placeholder\",\"Email\"],[24,3,\"email\"],[24,\"required\",\"\"],[24,4,\"email\"],[4,[38,2],[\"input\",[30,0,[\"updateEmailSignUp\"]]],null],[12],[13],[1,\"\\n        \"],[11,\"input\"],[24,\"placeholder\",\"Password\"],[24,3,\"password\"],[24,\"required\",\"\"],[24,4,\"password\"],[4,[38,2],[\"input\",[30,0,[\"updatePasswordSignUp\"]]],null],[12],[13],[1,\"\\n        \"],[8,[39,5],null,[[\"@type\"],[\"submit\"]],[[\"default\"],[[[[1,\"Create Account\"]],[]]]]],[1,\"\\n        \"],[10,2],[12],[1,\"Already have an account? \"],[8,[39,7],null,[[\"@route\"],[\"login\"]],[[\"default\"],[[[[1,\"Login here\"]],[]]]]],[13],[1,\"\\n        \"],[10,\"br\"],[12],[13],[1,\"\\n\"],[41,[30,0,[\"responseMsg\"]],[[[1,\"        \"],[10,2],[12],[10,\"strong\"],[12],[1,\"this.responseMsg\"],[13],[13],[1,\"\\n\"]],[]],null],[1,\"    \"],[13],[1,\"\\n\"],[13]],[],[\"div\",\"form\",\"on\",\"h2\",\"input\",\"auth-button\",\"p\",\"link-to\",\"br\",\"if\",\"strong\"]]",
    "moduleName": "movie-ticket-booking/templates/signup.hbs",
    "isStrictMode": false
  });
});
;define("movie-ticket-booking/transforms/boolean", ["exports", "@ember/debug", "@ember-data/serializer/transform"], function (_exports, _debug, _transform) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  Object.defineProperty(_exports, "default", {
    enumerable: true,
    get: function () {
      return _transform.BooleanTransform;
    }
  });
  0; //eaimeta@70e063a35619d71f0,"@ember/debug",0,"@ember-data/serializer/transform"eaimeta@70e063a35619d71f
  (true && !(false) && (0, _debug.deprecate)("You are relying on ember-data auto-magically installing the BooleanTransform. Use `export { BooleanTransform as default } from '@ember-data/serializer/transform';` in app/transforms/boolean.js instead", false, {
    id: 'ember-data:deprecate-legacy-imports',
    for: 'ember-data',
    until: '6.0',
    since: {
      enabled: '5.2',
      available: '4.13'
    }
  }));
});
;define("movie-ticket-booking/transforms/date", ["exports", "@ember/debug", "@ember-data/serializer/transform"], function (_exports, _debug, _transform) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  Object.defineProperty(_exports, "default", {
    enumerable: true,
    get: function () {
      return _transform.DateTransform;
    }
  });
  0; //eaimeta@70e063a35619d71f0,"@ember/debug",0,"@ember-data/serializer/transform"eaimeta@70e063a35619d71f
  (true && !(false) && (0, _debug.deprecate)("You are relying on ember-data auto-magically installing the DateTransform. Use `export { DateTransform as default } from '@ember-data/serializer/transform';` in app/transforms/date.js instead", false, {
    id: 'ember-data:deprecate-legacy-imports',
    for: 'ember-data',
    until: '6.0',
    since: {
      enabled: '5.2',
      available: '4.13'
    }
  }));
});
;define("movie-ticket-booking/transforms/number", ["exports", "@ember/debug", "@ember-data/serializer/transform"], function (_exports, _debug, _transform) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  Object.defineProperty(_exports, "default", {
    enumerable: true,
    get: function () {
      return _transform.NumberTransform;
    }
  });
  0; //eaimeta@70e063a35619d71f0,"@ember/debug",0,"@ember-data/serializer/transform"eaimeta@70e063a35619d71f
  (true && !(false) && (0, _debug.deprecate)("You are relying on ember-data auto-magically installing the NumberTransform. Use `export { NumberTransform as default } from '@ember-data/serializer/transform';` in app/transforms/number.js instead", false, {
    id: 'ember-data:deprecate-legacy-imports',
    for: 'ember-data',
    until: '6.0',
    since: {
      enabled: '5.2',
      available: '4.13'
    }
  }));
});
;define("movie-ticket-booking/transforms/string", ["exports", "@ember/debug", "@ember-data/serializer/transform"], function (_exports, _debug, _transform) {
  "use strict";

  Object.defineProperty(_exports, "__esModule", {
    value: true
  });
  Object.defineProperty(_exports, "default", {
    enumerable: true,
    get: function () {
      return _transform.StringTransform;
    }
  });
  0; //eaimeta@70e063a35619d71f0,"@ember/debug",0,"@ember-data/serializer/transform"eaimeta@70e063a35619d71f
  (true && !(false) && (0, _debug.deprecate)("You are relying on ember-data auto-magically installing the StringTransform. Use `export { StringTransform as default } from '@ember-data/serializer/transform';` in app/transforms/string.js instead", false, {
    id: 'ember-data:deprecate-legacy-imports',
    for: 'ember-data',
    until: '6.0',
    since: {
      enabled: '5.2',
      available: '4.13'
    }
  }));
});
;

;define('movie-ticket-booking/config/environment', [], function() {
  var prefix = 'movie-ticket-booking';
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

;
          if (!runningTests) {
            require("movie-ticket-booking/app")["default"].create({"name":"movie-ticket-booking","version":"0.0.0+fbe2c108"});
          }
        
//# sourceMappingURL=movie-ticket-booking.map
