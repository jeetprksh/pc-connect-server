export type Response = {
  status: boolean,
  message: string,
  data: Test | Item
};

export type Item = {
  name: string,
  isDirectory: boolean,
  isRoot: boolean,
  rootAlias: string
};

export type Test = {
  message: string
}