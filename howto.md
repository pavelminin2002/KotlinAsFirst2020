```
PS C:\Users\Pavel\IdeaProjects\github_3> git remote add upstream-my https://github.com/pavelminin2002/KotlinAsFirst2021
PS C:\Users\Pavel\IdeaProjects\github_3> git fetch upstream-my
remote: Enumerating objects: 417, done.
remote: Counting objects: 100% (34/34), done.
g objects:  95% (397/417)
Receiving objects: 100% (417/417), 171.81 KiB | 1.38 MiB/s, done.
Resolving deltas: 100% (184/184), completed with 9 local objects.
From https://github.com/pavelminin2002/KotlinAsFirst2021
* [new branch]      master     -> upstream-my/master
  PS C:\Users\Pavel\IdeaProjects\github_3> git rebase --onto master d535f3592006b8f2593c9f881d72c05164aadc13 upstream-my/master
  First, rewinding head to replay your work on top of it...
  Applying: Map: typo fix
  Applying: Fix test for lesson11.Complex.unaryMinus
  Applying: Fix test for lesson11.DimensionalValue (1 kg = 1000 g)
  Applying: Lesson 11: fix various complex tests
  Applying: Switch to proxy forwarders
  Applying: Update to 21.0.0
  Applying: Refresh chapter 00 for year 2021
  Applying: Fix local tests of pathBetweenHexes (see #103)
  Applying: Complex: change String-based constructor to factory method
  Applying: Rename test (lineByPoints) according to base function name
  Applying: Add test (circle on 3 non-diameter points) for minContainingCircle (#105)
  Applying: Drop task squareBetweenExists, see #100
  Applying: Chapter 7: minor fixes + explanation about try/finally and use (#104)
  Applying: Chapters 1-5: typo/style fixes
  Applying: Split chapter 10 into two chapters
  Applying: Add some task for lesson 10.2 (#80)
  Applying: Add draft of chapter 10.2 (#92)
  Applying: Tutorial fixes around minOrNull(), maxOrNull()
  Applying: Chapter 0: update links and minor details
  Applying: Chapter 0: some other FAQs
  Applying: Chapter 0: fix typo
  Applying: README: Update configuration manual link
  Applying: README: Update chapter 10 info
  Applying: Suppress unused parameters for some files
  Applying: Сделал в первом lesson
  Applying: Изменил 1 и 2 lesson
  Applying: Исправил 2 и 3 lesson
  Applying: Исправил 2 и 3 lesson
  Applying: Сделал несколько заданий в 4 lesson. И сделал небольшие поправки в 3 lesson. Еще мой ноутбук стал издавать странные звуки из-за чего решил сразу загрузить код, на всякий случ
  ай
  Applying: Сделал несколько задач в 5 лессоне
  Applying: Сделал несколько задач в 5 лессоне
  Applying:  Сделал последний номер в 4 lesson
  Using index info to reconstruct a base tree...
  M       src/lesson4/task1/List.kt
  Falling back to patching base and 3-way merge...
  Auto-merging src/lesson4/task1/List.kt
  CONFLICT (content): Merge conflict in src/lesson4/task1/List.kt
  error: Failed to merge in the changes.
  hint: Use 'git am --show-current-patch' to see the failed patch
  Patch failed at 0032  Сделал последний номер в 4 lesson
  Resolve all conflicts manually, mark them as resolved with
  "git add/rm <conflicted_files>", then run "git rebase --continue".
  You can instead skip this commit: run "git rebase --skip".
  To abort and get back to the state before "git rebase", run "git rebase --abort".
  PS C:\Users\Pavel\IdeaProjects\github_3> git rebase --abort
  PS C:\Users\Pavel\IdeaProjects\github_3> git rebase --onto master 258ad45bf6f7dd33dcbe65acd43f1194c31c735f upstream-my/master
  First, rewinding head to replay your work on top of it...
  Applying: Сделал в первом lesson
  Applying: Изменил 1 и 2 lesson
  Applying: Исправил 2 и 3 lesson
  Applying: Исправил 2 и 3 lesson
  Applying: Сделал несколько заданий в 4 lesson. И сделал небольшие поправки в 3 lesson. Еще мой ноутбук стал издавать странные звуки из-за чего решил сразу загрузить код, на всякий случ
  ай
  Applying: Сделал несколько задач в 5 лессоне
  Using index info to reconstruct a base tree...
  M       src/lesson4/task1/List.kt
  M       src/lesson5/task1/Map.kt
  Falling back to patching base and 3-way merge...
  Auto-merging src/lesson5/task1/Map.kt
  Auto-merging src/lesson4/task1/List.kt
  Applying: Сделал несколько задач в 5 лессоне
  Applying:  Сделал последний номер в 4 lesson
  Using index info to reconstruct a base tree...
  M       src/lesson4/task1/List.kt
  Falling back to patching base and 3-way merge...
  Auto-merging src/lesson4/task1/List.kt
  CONFLICT (content): Merge conflict in src/lesson4/task1/List.kt
  error: Failed to merge in the changes.
  hint: Use 'git am --show-current-patch' to see the failed patch
  Patch failed at 0008  Сделал последний номер в 4 lesson
  Resolve all conflicts manually, mark them as resolved with
  "git add/rm <conflicted_files>", then run "git rebase --continue".
  You can instead skip this commit: run "git rebase --skip".
  To abort and get back to the state before "git rebase", run "git rebase --abort".
  PS C:\Users\Pavel\IdeaProjects\github_3> git checkout --theirs src/lesson4/task1/List.kt
  Updated 1 path from the index
  PS C:\Users\Pavel\IdeaProjects\github_3> git add src/lesson4/task1/List.kt
  PS C:\Users\Pavel\IdeaProjects\github_3> git rebase --continue
  Applying:  Сделал последний номер в 4 lesson
  Applying:  Сделал последний номер в 4 lesson
  Applying: Сделал несколько задач в 5 лессоне
  Using index info to reconstruct a base tree...
  M       src/lesson4/task1/List.kt
  M       src/lesson5/task1/Map.kt
  Falling back to patching base and 3-way merge...
  Auto-merging src/lesson5/task1/Map.kt
  Applying: Исправил в 4 и 5 . Сделал несколько задач в 6
  Using index info to reconstruct a base tree...
  M       src/lesson5/task1/Map.kt
  Falling back to patching base and 3-way merge...
  Auto-merging src/lesson5/task1/Map.kt
  Applying: Исправил вывод firstDuplicateIndex
  Applying: исправил недочет в 5
  Applying: исправил недочет в 5
  Applying: Сделал задачу в 7 lesson
  Applying: Сделал задачи в 7 lesson, а так же исправил некоторые моменты в 6 lesson
  Applying: Сделал исправления в последней задаче 7 lesson
  Applying: Сделал исправления в последней задаче 7 lesson
  Applying: проверка
  Applying: проверка
  Applying: исправил последнее задание в 7 lesson
  Applying: исправил последнее задание в 7 lesson
  Applying: исправил последнее задание в 7 lesson
  Applying: исправил последнее задание в 7 lesson
  Applying: исправил последнее задание в 7 lesson
  Applying: сократил последнее задание в 7 lesson
  Applying: 7 lesson поправки
  Applying: 5 lesson
  Applying: 7 lesson последняя проклятая задача
  Applying: 7 lesson последняя задача
  Applying: просто ресабмит на всякий случай
  Applying: исправил в 5 lesson
  Applying: исправил в 5 lesson
  Applying: 7 lesson
  Applying: 4 lesson
  Applying: 5 lessson
  Applying: 3 lesson
  Applying: 7 lesson
  Applying: 7 lesson
  PS C:\Users\Pavel\IdeaProjects\github_3> git branch backport
  PS C:\Users\Pavel\IdeaProjects\github_3> git checkout master
  Previous HEAD position was 6ed3b1b 7 lesson
  Switched to branch 'master'
  Your branch is up to date with 'origin/master'.
  PS C:\Users\Pavel\IdeaProjects\github_3> git checkout -
  Note: switching to '6ed3b1b8f205e9762da455a240047e46565a8b93'.

You are in 'detached HEAD' state. You can look around, make experimental
changes and commit them, and you can discard any commits you make in this
state without impacting any branches by switching back to a branch.

If you want to create a new branch to retain commits you create, you may
do so (now or later) by using -c with the switch command. Example:

git switch -c <new-branch-name>

Or undo this operation with:

git switch -

Turn off this advice by setting config variable advice.detachedHead to false

HEAD is now at 6ed3b1b 7 lesson
PS C:\Users\Pavel\IdeaProjects\github_3> git checkout -b backport
fatal: A branch named 'backport' already exists.
PS C:\Users\Pavel\IdeaProjects\github_3> git branch -D backport
Deleted branch backport (was 6ed3b1b).
PS C:\Users\Pavel\IdeaProjects\github_3> git checkout -b backport
Switched to a new branch 'backport'
PS C:\Users\Pavel\IdeaProjects\github_3> git checkout master
Switched to branch 'master'
Your branch is up to date with 'origin/master'.
PS C:\Users\Pavel\IdeaProjects\github_3> git merge backport
Updating d535f35..6ed3b1b
Fast-forward
src/lesson1/task1/Simple.kt  |  20 +--
src/lesson2/task1/IfElse.kt  |  53 +++++++-
src/lesson2/task2/Logical.kt |  28 +++-
src/lesson3/task1/Loop.kt    | 144 ++++++++++++++++++--
src/lesson4/task1/List.kt    | 134 +++++++++++++++++--
src/lesson5/task1/Map.kt     | 104 +++++++++++++--
src/lesson6/task1/Parse.kt   |  52 +++++++-
src/lesson7/task1/Files.kt   | 307 +++++++++++++++++++++++++++++++------------
8 files changed, 699 insertions(+), 143 deletions(-)
PS C:\Users\Pavel\IdeaProjects\github_3> git remote add upstream-theirs https://github.com/kuzichevapd/KotlinAsFirst2021
PS C:\Users\Pavel\IdeaProjects\github_3> git fetch upstream-theirs
remote: Enumerating objects: 423, done.
remote: Counting objects: 100% (114/114), done.
Receiving objects:  86% (364/423)used 114 (delta 114), pack-reused 309R
Receiving objects: 100% (423/423), 43.72 KiB | 2.43 MiB/s, done.
Resolving deltas: 100% (224/224), completed with 19 local objects.
From https://github.com/kuzichevapd/KotlinAsFirst2021
* [new branch]      master     -> upstream-theirs/master
  PS C:\Users\Pavel\IdeaProjects\github_3> git merge -s ours upstream-theirs/master
  Merge made by the 'ours' strategy.
  PS C:\Users\Pavel\IdeaProjects\github_3> git remote -v > remotes
  PS C:\Users\Pavel\IdeaProjects\github_3> git add remotes
  PS C:\Users\Pavel\IdeaProjects\github_3> git commit -m "Add remotes file"
  [master 69d9d8e] Add remotes file
  1 file changed, 0 insertions(+), 0 deletions(-)
  create mode 100644 remotes
  PS C:\Users\Pavel\IdeaProjects\github_3> touch howto.md
  touch : Имя "touch" не распознано как имя командлета, функции, файла сценария или выполняемой программы. Проверьте правильность написания имени, а также наличие и правильность пути, п
  осле чего повторите попытку.
  строка:1 знак:1
+ touch howto.md
+ ~~~~~
    + CategoryInfo          : ObjectNotFound: (touch:String) [], CommandNotFoundException
    + FullyQualifiedErrorId : CommandNotFoundException

PS C:\Users\Pavel\IdeaProjects\github_3> git add howto.md
PS C:\Users\Pavel\IdeaProjects\github_3> git commit -m "Add howto.md"
[master 7f0a91f] Add howto.md
1 file changed, 0 insertions(+), 0 deletions(-)
create mode 100644 howto.md
PS C:\Users\Pavel\IdeaProjects\github_3>
```